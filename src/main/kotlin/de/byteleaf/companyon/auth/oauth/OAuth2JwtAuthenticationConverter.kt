package de.byteleaf.companyon.auth.oauth

import de.byteleaf.companyon.auth.logic.AuthInfoService
import de.byteleaf.companyon.user.logic.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

@Component
class OAuth2JwtAuthenticationConverter : Converter<Jwt, OAuth2AuthenticationToken> {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var authInfoService: AuthInfoService

    override fun convert(source: Jwt): OAuth2AuthenticationToken {
        val oauth2Subject = source.getClaimAsString("sub")

        // Try to find the user by the oauth subject in the database
        val dbUser = userService.findByOAuth2Subject(oauth2Subject)
        if (dbUser != null) return OAuth2AuthenticationToken.create(dbUser, source.claims)

        val userInfo = authInfoService.loadUserInfo(source.tokenValue)

        // Try to find the user by email. A new created user who never logged in could only be found by the email address!
        return OAuth2AuthenticationToken.create(
            userService.activateNewUser(userInfo["email"] as String, oauth2Subject),
            source.claims
        )
    }
}