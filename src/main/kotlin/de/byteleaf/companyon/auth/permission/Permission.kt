package de.byteleaf.companyon.auth.permission

interface Permission {

    fun hasPermission(id: String?): Boolean

    fun getPermissionType(): PermissionType
}