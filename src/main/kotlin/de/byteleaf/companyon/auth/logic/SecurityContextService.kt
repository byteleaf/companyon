package de.byteleaf.companyon.auth.logic

import de.byteleaf.companyon.user.dto.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SecurityContextService {

    /**
     * To get the current logged in user
     */
    fun getCurrentUser(): User = SecurityContextHolder.getContext().authentication.principal as User

    fun isCurrentUserOrAdmin(userId: String): Boolean = getCurrentUser().admin || getCurrentUser().id!!.equals(userId)
}