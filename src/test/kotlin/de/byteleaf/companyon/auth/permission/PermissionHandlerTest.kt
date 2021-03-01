package de.byteleaf.companyon.auth.permission

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
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
        assertTrue(permissionHandler.hasPermissions(emptyArray()))
    }

    @Test
    fun onePermissionSuccess() {
        setSecurityContext(true)
        assertTrue(permissionHandler.hasPermissions(arrayOf(Pair(PermissionType.ADMIN, null))))
    }

    @Test
    fun onePermissionFail() {
        assertFalse(permissionHandler.hasPermissions(arrayOf(Pair(PermissionType.ADMIN, null))))
    }

    @Test
    fun twoPermissionSuccess() {
        setSecurityContext(true)
        assertTrue(permissionHandler.hasPermissions(arrayOf(Pair(PermissionType.ADMIN, null), Pair(PermissionType.CURRENT_USER_OR_ADMIN, null))))
    }

    @Test
    fun oneSuccessOneFail() {
        assertFalse(permissionHandler.hasPermissions(arrayOf(Pair(PermissionType.ADMIN, null), Pair(PermissionType.CURRENT_USER_OR_ADMIN, NonSecConfiguration.NON_SEC_USER_ID))))
    }
}