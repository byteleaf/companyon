package de.byteleaf.companyon.common.auth

import de.byteleaf.companyon.fileupload.dto.input.FileMetaInput
import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.input.UserInput
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.core.convert.converter.Converter
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

@Component
class OAuth2JwtAuthenticationConverter(
        @Value("\${spring.security.oauth2.resource.user-info-uri}") private val userInfoUri: String,
        private val userService: UserService
) : Converter<Jwt, OAuth2AuthenticationToken> {

    override fun convert(source: Jwt): OAuth2AuthenticationToken? {
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

        val createDBUser = userService.create(UserInput(
                map["sub"] as String,
                map["given_name"] as? String ?: "",
                map["family_name"] as? String ?: "",
                map["email"] as String,
                FileMetaInput("", "", ""),
                null
        ))

        return OAuth2AuthenticationToken.create(createDBUser, source.claims)
    }

}