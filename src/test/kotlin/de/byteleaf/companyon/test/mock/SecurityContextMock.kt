package de.byteleaf.companyon.test.mock

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.auth.oauth.OAuth2AuthenticationToken
import de.byteleaf.companyon.user.dto.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityContextMock {

    @Value("\${app.non-sec-user-oauth2-subject}")
    private lateinit var nonSecUserOAuth2Subject: String

    @Value("\${app.non-sec-user-admin}")
    private var nonSecUserAdmin: Boolean = false

    fun set(isAdmin: Boolean = nonSecUserAdmin) {
        val securityContext = SecurityContextHolder.createEmptyContext()
        securityContext.authentication = OAuth2AuthenticationToken.create(getUser(isAdmin), null)
        SecurityContextHolder.setContext(securityContext)
    }

    fun getUser(isAdmin: Boolean = nonSecUserAdmin): User {
        val user = User(nonSecUserOAuth2Subject, "Jeff", "Bytezos", "jeff@byteleaf.de", isAdmin, null, null)
        user.id = NonSecConfiguration.NON_SEC_USER_ID
        return user
    }
}