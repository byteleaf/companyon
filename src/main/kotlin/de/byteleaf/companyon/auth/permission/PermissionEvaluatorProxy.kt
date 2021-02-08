package de.byteleaf.companyon.auth.permission

import de.byteleaf.companyon.common.error.exception.FatalException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class PermissionEvaluatorProxy : PermissionEvaluator {

    @Autowired
    private lateinit var permissionHandler: PermissionHandler

    override fun hasPermission(authentication: Authentication?, param1: Any?, param2: Any?): Boolean {
        if(param1 is PermissionType && param2 is String) {
            return permissionHandler.hasPermission(param1, param2)
        }
        throw FatalException("Not implemented")
    }

    override fun hasPermission(
        authentication: Authentication?,
        serializable: Serializable?,
        s: String?,
        o: Any?
    ): Boolean {
        throw FatalException("Not implemented")
    }
}