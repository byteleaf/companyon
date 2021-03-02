package de.byteleaf.companyon.test.mock

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.auth.oauth.OAuth2AuthenticationToken
import de.byteleaf.companyon.user.dto.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Profile("non-sec")
@Component
class SecurityContextMock {

    @Value("\${app.non-sec-user-admin}")
    private var nonSecUserAdmin: Boolean = false

    fun set(isAdmin: Boolean = nonSecUserAdmin) {
        val securityContext = SecurityContextHolder.createEmptyContext()
        securityContext.authentication = OAuth2AuthenticationToken.create(getUser(isAdmin), null)
        SecurityContextHolder.setContext(securityContext)
    }

    fun getUser(isAdmin: Boolean = nonSecUserAdmin): User {
        val user = User(NonSecConfiguration.NON_SEC_OAUTH_SUBJECT, NonSecConfiguration.NON_SEC_FIRST_NAME, NonSecConfiguration.NON_SEC_LAST_NAME, NonSecConfiguration.NON_SEC_EMAIL, isAdmin, null, null)
        user.id = NonSecConfiguration.NON_SEC_USER_ID
        return user
    }
}