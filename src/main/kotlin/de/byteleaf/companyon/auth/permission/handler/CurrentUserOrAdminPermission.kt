package de.byteleaf.companyon.auth.permission.handler

import de.byteleaf.companyon.auth.permission.Permission
import de.byteleaf.companyon.auth.permission.PermissionType
import de.byteleaf.companyon.common.error.ErrorExtensionKey
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CurrentUserOrAdminPermission : Permission() {

    @Autowired
    private lateinit var adminPermission: AdminPermission

    /**
     * @param id if null the user must be admin! If not null the id must be the id of the current user.
     */
    override fun hasPermission(id: String?, skipError: Boolean): Boolean {
        val currentUser = securityContextService.getCurrentUser()
        if(id == null || currentUser.admin) {
            return adminPermission.hasPermission(id, skipError)
        }

        return if(id.equals(currentUser.id)) true
        else noPermission( skipError, "The current user is not allowed to access or modify data from user $id.", ErrorExtensionKey.TARGET_USER_ID, id)
    }

    override fun getPermissionType(): PermissionType = PermissionType.CURRENT_USER_OR_ADMIN
}