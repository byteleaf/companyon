package de.byteleaf.companyon.test.mock

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.auth.oauth.OAuth2AuthenticationToken
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.entity.UserEntity
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityContextMock {

    @Value("\${app.non-sec-user-admin}")
    private var nonSecUserAdmin: Boolean = false

    @Autowired
    protected lateinit var modelMapper: ModelMapper

    fun set(isAdmin: Boolean = nonSecUserAdmin) {
        val securityContext = SecurityContextHolder.createEmptyContext()
        securityContext.authentication = OAuth2AuthenticationToken.create(getUser(isAdmin), null)
        SecurityContextHolder.setContext(securityContext)
    }

    fun getUser(isAdmin: Boolean = nonSecUserAdmin): User {
        val entity =
            modelMapper.map(UserInput(NonSecConfiguration.NON_SEC_FIRST_NAME, NonSecConfiguration.NON_SEC_LAST_NAME, NonSecConfiguration.NON_SEC_EMAIL, nonSecUserAdmin), UserEntity::class.java)
        entity.id = NonSecConfiguration.NON_SEC_USER_ID
        entity.oauth2Subject = NonSecConfiguration.NON_SEC_OAUTH_SUBJECT
        return modelMapper.map(entity, User::class.java)
    }
}
