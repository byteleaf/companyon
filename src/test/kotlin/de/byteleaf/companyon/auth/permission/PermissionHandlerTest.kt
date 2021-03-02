package de.byteleaf.companyon.auth.permission

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.test.mock.SecurityContextMock
import de.byteleaf.companyon.test.AbstractAuthenticatedTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.security.core.context.SecurityContextHolder

class PermissionHandlerTest : AbstractAuthenticatedTest() {

    @Test
    fun notLoggedIn() {
        SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext())
        assertThrows(IllegalStateException::class.java) {
            permissionHandler.hasPermissions()
        }
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
        assertFalse(permissionHandler.hasPermissions(listOf(Pair(PermissionType.ADMIN, null))))
    }

    @Test
    fun twoPermissionSuccess() {
        securityContextMock.set(true)
        assertTrue(permissionHandler.hasPermissions(listOf(Pair(PermissionType.ADMIN, null), Pair(PermissionType.CURRENT_USER_OR_ADMIN, null))))
    }

    @Test
    fun oneSuccessOneFail() {
        assertFalse(permissionHandler.hasPermissions(listOf(Pair(PermissionType.ADMIN, null), Pair(PermissionType.CURRENT_USER_OR_ADMIN, NonSecConfiguration.NON_SEC_USER_ID))))
    }
}