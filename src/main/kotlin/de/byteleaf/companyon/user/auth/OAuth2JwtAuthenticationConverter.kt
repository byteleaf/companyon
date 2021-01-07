package de.byteleaf.companyon.user.auth

import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.input.UserInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.core.convert.converter.Converter
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

@Component
class OAuth2JwtAuthenticationConverter : Converter<Jwt, OAuth2AuthenticationToken> {

    @Value("\${spring.security.oauth2.resource.user-info-uri}")
    private lateinit var userInfoUri: String

    @Autowired
    private lateinit var userService: UserService

    override fun convert(source: Jwt): OAuth2AuthenticationToken {
        val oauth2Subject = source.getClaimAsString("sub")

        // Try to find the user by the oauth subject in the database
        val dbUser = userService.findByOauth2Subject(oauth2Subject)
        if (dbUser != null) return OAuth2AuthenticationToken.create(dbUser, source.claims)

        val userInfo = loadUserInfo(source.tokenValue)

        // Try to find the user by email. A new created user who never logged in could only be found by the email address!
        val dbUserEmail = userService.findByEmailAndUpdateOAuthSubject(userInfo["email"] as String, oauth2Subject)
        if (dbUserEmail != null) {
            return OAuth2AuthenticationToken.create(dbUserEmail, source.claims)
        }

        throw Exception("Your not allowed to use this application")
    }


    private fun loadUserInfo(tokenValue: String): Map<String, String> {
        val restTemplate = RestTemplateBuilder()
            .defaultHeader("Authorization", "Bearer $tokenValue")
            .build()

        @Suppress("UNCHECKED_CAST")
        return restTemplate.getForEntity(userInfoUri, Map::class.java).body as Map<String, String>?
            ?: throw Exception("Can't load user meta data from")
    }

}