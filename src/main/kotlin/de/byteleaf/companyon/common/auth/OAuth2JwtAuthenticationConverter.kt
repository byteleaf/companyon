package de.byteleaf.companyon.common.auth

import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.input.UserInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.core.convert.converter.Converter
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

// TODO implement Test
@Component
class OAuth2JwtAuthenticationConverter : Converter<Jwt, OAuth2AuthenticationToken> {

    @Value("\${spring.security.oauth2.resource.user-info-uri}")
    private lateinit var userInfoUri: String

    @Autowired
    private lateinit var userService: UserService

    override fun convert(source: Jwt): OAuth2AuthenticationToken? {
        val oauth2Subject = source.getClaimAsString("sub")
        val dbUser = userService.byOAuth2Subject(source.getClaimAsString("sub"))

        if (dbUser != null) {
            return OAuth2AuthenticationToken.create(dbUser, source.claims)
        }

        val restTemplate = RestTemplateBuilder()
            .defaultHeader("Authorization", "Bearer ${source.tokenValue}")
            .build()

        @Suppress("UNCHECKED_CAST")
        val map = restTemplate.getForEntity(userInfoUri, Map::class.java).body as Map<String, *>?

        if (map == null) {
            print("map is nil!")
            return null
        }

        val createDBUser = userService.create(
            UserInput(
                map["sub"] as String,
                map["given_name"] as? String ?: "",
                map["family_name"] as? String ?: "",
                map["email"] as String
            ),
            oauth2Subject
        )

        return OAuth2AuthenticationToken.create(createDBUser, source.claims)
    }

}