package de.byteleaf.companyon.security.control

import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class PermissionEvaluatorProxy : PermissionEvaluator {

    override fun hasPermission(authentication: Authentication?, o: Any?, o1: Any?): Boolean {
        return true
    }

    override fun hasPermission(
        authentication: Authentication?,
        serializable: Serializable?,
        s: String?,
        o: Any?
    ): Boolean {
        return true
    }
}