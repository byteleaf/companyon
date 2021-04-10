package de.byteleaf.companyon.auth.permission

import de.byteleaf.companyon.auth.logic.SecurityContextService
import de.byteleaf.companyon.common.error.exception.FatalException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class PermissionHandler constructor(@Autowired permissions: Set<Permission>) {

    private var permissionHandlers: HashMap<PermissionType, Permission> = HashMap()

    @Autowired
    private lateinit var securityContextService: SecurityContextService

    init {
        permissions.forEach { permissionHandler -> permissionHandlers.put(permissionHandler.getPermissionType(), permissionHandler) }
    }

    fun hasPermissions(permissions: List<Pair<PermissionType, *>>? = emptyList(), skipError: Boolean = false): Boolean {
        // Make sure the user is really logged in, if not an IllegalState exception will be thrown!
        securityContextService.getCurrentUser()

        if (permissions.isNullOrEmpty()) return true

        return !permissions.any {
            @Suppress("UNCHECKED_CAST")
            return when (it.second) {
                null -> hasPermission(it.first, null, skipError)
                is String -> hasPermission(it.first, it.second as String, skipError)
                is Collection<*> -> hasPermissionForMultiple(it.first, it.second as Collection<String>, skipError)
                else -> throw FatalException("No permission handler found for delivered type of the id parameter")
            }
        }
    }

    /**
     * To check if the current user has the permission
     * @param permissionType the type of the permission to detect the convenient handler
     * @param id the id of entity where the permission is requested
     * @param skipError if set false will be returned instead of a exception
     * @throws PermissionException will be thrown instead of a false return value if the [skipError] flag is not set
     */
    fun hasPermission(permissionType: PermissionType, id: String? = null, skipError: Boolean = false): Boolean {
        val permissionHandler = permissionHandlers[permissionType]
            ?: throw FatalException("No permission handler found for type ${permissionType.name}")

        return permissionHandler.hasPermission(id, skipError)
    }

    /**
     * To check if the current user has the permissions for a list of ids
     * @param permissionType the type of the permission to detect the convenient handler
     * @param ids a list of entity ids where the permission are requested for
     * @param skipError if set false will be returned instead of a exception
     * @throws PermissionException will be thrown instead of a false return value if the [skipError] flag is not set
     */
    fun hasPermissionForMultiple(permissionType: PermissionType, ids: Collection<String>? = null, skipError: Boolean = false): Boolean {
        if (ids.isNullOrEmpty()) {
            return hasPermission(permissionType, null, skipError)
        }
        return ids.all {
            hasPermission(permissionType, it, skipError)
        }
    }
}
