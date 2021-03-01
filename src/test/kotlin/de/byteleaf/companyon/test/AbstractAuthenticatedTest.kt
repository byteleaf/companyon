package de.byteleaf.companyon.test

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.auth.logic.SecurityContextService
import de.byteleaf.companyon.auth.oauth.OAuth2AuthenticationToken
import de.byteleaf.companyon.auth.permission.PermissionHandler
import de.byteleaf.companyon.auth.permission.handler.AdminPermission
import de.byteleaf.companyon.auth.permission.handler.CurrentUserOrAdminPermission
import de.byteleaf.companyon.user.dto.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.PropertySource
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * Base class for all unittests which require a user, authentication and permission context!
 */
@Import(PermissionHandler::class, AdminPermission::class, CurrentUserOrAdminPermission::class, SecurityContextService::class)
@ActiveProfiles(profiles = ["test"])
@ExtendWith(SpringExtension::class)
@PropertySource("classpath:/application-test.properties")
abstract class AbstractAuthenticatedTest {

    @Value("\${app.non-sec-user-oauth2-subject}")
    private lateinit var nonSecUserOAuth2Subject: String
    @Value("\${app.non-sec-user-admin}")
    private var nonSecUserAdmin: Boolean = false
    @Autowired
    protected lateinit var permissionHandler: PermissionHandler

    @BeforeEach
    protected fun setupSecurityContext() {
        setSecurityContext(nonSecUserAdmin)
    }

    protected fun setSecurityContext(isAdmin: Boolean) {
        val user = User(nonSecUserOAuth2Subject, "Jeff", "Bytezos", "jeff@byteleaf.de", isAdmin, null, null)
        user.id = NonSecConfiguration.NON_SEC_USER_ID
        val securityContext = SecurityContextHolder.createEmptyContext()
        securityContext.authentication = OAuth2AuthenticationToken.create(user, null)
        SecurityContextHolder.setContext(securityContext)
    }

}