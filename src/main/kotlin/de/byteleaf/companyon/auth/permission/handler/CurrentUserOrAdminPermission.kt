package de.byteleaf.companyon.auth.permission.handler

import de.byteleaf.companyon.auth.logic.SecurityContextService
import de.byteleaf.companyon.auth.permission.Permission
import de.byteleaf.companyon.auth.permission.PermissionType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CurrentUserOrAdminPermission: Permission {

    @Autowired
    private lateinit var securityContextService: SecurityContextService

    override fun hasPermission(id: String?): Boolean {
        if(securityContextService.getCurrentUser().admin) return true
        if(id == null) return false
        return id.equals(securityContextService.getCurrentUser().id!!)
        // TODO throw exception
    }

    override fun getPermissionType(): PermissionType = PermissionType.CURRENT_USER_OR_ADMIN
}