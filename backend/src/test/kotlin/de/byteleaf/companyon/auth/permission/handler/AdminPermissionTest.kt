package de.byteleaf.companyon.auth.permission.handler

import de.byteleaf.companyon.auth.permission.AbstractAuthenticatedTest
import de.byteleaf.companyon.auth.permission.PermissionType
import de.byteleaf.companyon.test.util.ExceptionUtil
import graphql.Assert.assertFalse
import graphql.Assert.assertTrue
import org.junit.jupiter.api.Test

class AdminPermissionTest : AbstractAuthenticatedTest() {

    @Test
    fun hasPermissionSuccess() {
        securityContextMock.set(true)
        assertTrue(permissionHandler.hasPermission(PermissionType.ADMIN))
    }

    @Test
    fun hasPermissionFail() {
        assertFalse(permissionHandler.hasPermission(PermissionType.ADMIN, null, true))
    }

    @Test
    fun hasPermissionFailWithError() {
        ExceptionUtil.expectPermissionException(PermissionType.ADMIN) {
            permissionHandler.hasPermission(PermissionType.ADMIN)
        }
    }
}
