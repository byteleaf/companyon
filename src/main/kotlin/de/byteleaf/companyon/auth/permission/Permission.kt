package de.byteleaf.companyon.auth.permission

interface Permission {

    fun hasPermission(): Boolean

    fun getPermissionType(): PermissionType
}