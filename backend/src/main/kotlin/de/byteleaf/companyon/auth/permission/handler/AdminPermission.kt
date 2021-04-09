package de.byteleaf.companyon.auth.permission.handler

import de.byteleaf.companyon.auth.permission.Permission
import de.byteleaf.companyon.auth.permission.PermissionType
import org.springframework.stereotype.Component

@Component
class AdminPermission : Permission() {

    override fun hasPermission(id: String?, skipError: Boolean): Boolean = if (securityContextService.getCurrentUser().admin) true else
        noPermission(skipError, "This action can only be performed by a user with admin role!")

    override fun getPermissionType(): PermissionType = PermissionType.ADMIN

    fun throwException(message: String) {
        noPermission(false, message)
    }
}
