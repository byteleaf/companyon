package de.byteleaf.companyon.auth.permission

import de.byteleaf.companyon.auth.logic.SecurityContextService
import de.byteleaf.companyon.auth.permission.handler.AdminPermission
import de.byteleaf.companyon.auth.permission.handler.CurrentUserOrAdminPermission
import de.byteleaf.companyon.common.configuration.ModelMapperConfiguration
import de.byteleaf.companyon.test.mock.SecurityContextMock
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.PropertySource
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * Base class for all unittests which require a user, authentication and permission context!
 */
@Import(PermissionHandler::class, AdminPermission::class, CurrentUserOrAdminPermission::class, SecurityContextService::class, SecurityContextMock::class, ModelMapperConfiguration::class)
@ActiveProfiles(profiles = ["non-sec", "test"])
@ExtendWith(SpringExtension::class)
@PropertySource("classpath:/application-test.properties")
abstract class AbstractAuthenticatedTest {

    @Autowired
    protected lateinit var permissionHandler: PermissionHandler

    @Autowired
    protected lateinit var securityContextMock: SecurityContextMock

    @BeforeEach
    protected fun setupSecurityContext() {
        securityContextMock.set()
    }
}
