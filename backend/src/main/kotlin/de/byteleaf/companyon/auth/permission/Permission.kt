package de.byteleaf.companyon.auth.permission

import de.byteleaf.companyon.auth.logic.SecurityContextService
import de.byteleaf.companyon.common.error.ErrorExtensionKey
import org.springframework.beans.factory.annotation.Autowired

abstract class Permission {

    @Autowired
    protected lateinit var securityContextService: SecurityContextService

    abstract fun hasPermission(id: String?, skipError: Boolean = false): Boolean

    abstract fun getPermissionType(): PermissionType

    /**
     * @return false or throws a [PermissionException] depending on the [skipError] flag.
     * It is appending the id of the current user id to [PermissionException.parameters]!
     * @param permissionType if not specified the [getPermissionType] value will be used
     * @throws PermissionException if [skipError] is false
     */
    fun noPermission(skipError: Boolean, message: String, key1: ErrorExtensionKey? = null, value1: String? = null, permissionType: PermissionType? = null): Boolean {
        if (skipError) return false
        val parameters = mutableMapOf<ErrorExtensionKey, Any?>()
        if (key1 != null && value1 != null) parameters[key1] = value1
        parameters[ErrorExtensionKey.CURRENT_USER_ID] = securityContextService.getCurrentUser().id
        throw PermissionException(permissionType ?: getPermissionType(), message, parameters)
    }
}
