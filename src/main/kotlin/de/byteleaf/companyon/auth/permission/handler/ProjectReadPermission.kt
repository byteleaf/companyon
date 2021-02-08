package de.byteleaf.companyon.auth.permission.handler

import de.byteleaf.companyon.auth.permission.Permission
import de.byteleaf.companyon.auth.permission.PermissionType

class ProjectReadPermission : Permission {
    override fun hasPermission(): Boolean {
        // TODO
        return false
    }

    override fun getPermissionType(): PermissionType = PermissionType.PROJECT_READ
}