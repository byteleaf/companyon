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
        val a = param1 is PermissionType
        val b = (param2 is String || param2 == null)
        if(param1 is PermissionType && (param2 is String || param2 == null)) {
            return permissionHandler.hasPermission(param1, param2 as String)
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