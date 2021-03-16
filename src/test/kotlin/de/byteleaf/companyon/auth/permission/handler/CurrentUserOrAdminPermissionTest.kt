package de.byteleaf.companyon.auth.permission.handler

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.auth.permission.PermissionType
import de.byteleaf.companyon.auth.permission.AbstractAuthenticatedTest
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class CurrentUserOrAdminPermissionTest : AbstractAuthenticatedTest() {

    @Test
    fun adminAllUsers() {
        securityContextMock.set(true)
        assertTrue(permissionHandler.hasPermission(PermissionType.CURRENT_USER_OR_ADMIN))
    }

    @Test
    fun noAdminAllUsers() {
        assertFalse(permissionHandler.hasPermission(PermissionType.CURRENT_USER_OR_ADMIN, null, true))
    }

    @Test
    fun noAdminCurrentUser() {
        assertTrue(permissionHandler.hasPermission(PermissionType.CURRENT_USER_OR_ADMIN, NonSecConfiguration.NON_SEC_USER_ID))
    }

    @Test
    fun noAdminNotCurrentUser() {
        assertFalse(permissionHandler.hasPermission(PermissionType.CURRENT_USER_OR_ADMIN, "123", true))
    }

    @Test
    fun adminNotCurrentUser() {
        securityContextMock.set(true)
        assertTrue(permissionHandler.hasPermission(PermissionType.CURRENT_USER_OR_ADMIN, "123"))
    }
}