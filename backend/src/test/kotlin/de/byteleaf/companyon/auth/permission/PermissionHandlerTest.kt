package de.byteleaf.companyon.auth.permission

import com.mongodb.internal.connection.tlschannel.util.Util.assertTrue
import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.test.util.ExceptionUtil
import graphql.Assert.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.security.core.context.SecurityContextHolder

class PermissionHandlerTest : AbstractAuthenticatedTest() {

    @Test
    fun notLoggedIn() {
        SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext())
        assertThrows(IllegalStateException::class.java) { permissionHandler.hasPermissions() }
    }

    @Test
    fun emptyPermissionList() {
        assertTrue(permissionHandler.hasPermissions(emptyList()))
    }

    @Test
    fun onePermissionSuccess() {
        securityContextMock.set(true)
        assertTrue(permissionHandler.hasPermissions(listOf(Pair(PermissionType.ADMIN, null))))
    }

    @Test
    fun onePermissionFail() {
        assertFalse(permissionHandler.hasPermissions(listOf(Pair(PermissionType.ADMIN, null)), true))
    }

    @Test
    fun onePermissionFailThrowError() {
        ExceptionUtil.expectPermissionException(PermissionType.ADMIN) {
            permissionHandler.hasPermissions(listOf(Pair(PermissionType.ADMIN, null)))
        }
    }

    @Test
    fun twoPermissionSuccess() {
        securityContextMock.set(true)
        assertTrue(permissionHandler.hasPermissions(listOf(Pair(PermissionType.ADMIN, null), Pair(PermissionType.CURRENT_USER_OR_ADMIN, null))))
    }

    @Test
    fun oneSuccessOneFail() {
        assertFalse(permissionHandler.hasPermissions(listOf(Pair(PermissionType.ADMIN, null), Pair(PermissionType.CURRENT_USER_OR_ADMIN, NonSecConfiguration.NON_SEC_USER_ID)), true))
    }

    @Test
    fun hasPermissionForMultiple_IdsNull() {
        assertFalse(permissionHandler.hasPermissions(listOf(Pair(PermissionType.CURRENT_USER_OR_ADMIN, null)), true))
        securityContextMock.set(true)
        assertTrue(permissionHandler.hasPermissions(listOf(Pair(PermissionType.CURRENT_USER_OR_ADMIN, null))))
    }

    @Test
    fun hasPermissionForMultiple_IdsEmpty() {
        assertFalse(permissionHandler.hasPermissions(listOf(Pair(PermissionType.CURRENT_USER_OR_ADMIN, emptyList<String>())), true))
        securityContextMock.set(true)
        assertTrue(permissionHandler.hasPermissions(listOf(Pair(PermissionType.CURRENT_USER_OR_ADMIN, emptyList<String>()))))
    }

    @Test
    fun hasPermissionForMultiple_OneId() {
        assertTrue(permissionHandler.hasPermissions(listOf(Pair(PermissionType.CURRENT_USER_OR_ADMIN, listOf(NonSecConfiguration.NON_SEC_USER_ID)))))
        assertFalse(permissionHandler.hasPermissions(listOf(Pair(PermissionType.CURRENT_USER_OR_ADMIN, listOf("other_id"))), true))
    }

    @Test
    fun hasPermissionForMultiple_TwoIds() {
        assertFalse(permissionHandler.hasPermissions(listOf(Pair(PermissionType.CURRENT_USER_OR_ADMIN, listOf(NonSecConfiguration.NON_SEC_USER_ID, "other_id"))), true))
        securityContextMock.set(true)
        assertTrue(permissionHandler.hasPermissions(listOf(Pair(PermissionType.CURRENT_USER_OR_ADMIN, listOf(NonSecConfiguration.NON_SEC_USER_ID, "other_id"))), true))
    }
}
