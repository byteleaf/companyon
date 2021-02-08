package de.byteleaf.companyon.auth.permission

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*


@Component
class PermissionHandler constructor(@Autowired permissions: Set<Permission>) {

    private var permissionHandlers: HashMap<PermissionType, Permission> = HashMap()

    init {
        permissions.forEach { permissionHandler -> permissionHandlers.put(permissionHandler.getPermissionType(),  permissionHandler) }
    }

    fun hasPermission(permissionType: PermissionType, id: String?): Boolean {
        val permissionHandler = permissionHandlers[permissionType]

        return true
    }


}