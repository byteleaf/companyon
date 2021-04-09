package de.byteleaf.companyon.auth.logic

import de.byteleaf.companyon.auth.dto.Approval
import de.byteleaf.companyon.user.dto.output.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class SecurityContextService {

    /**
     * To get the current logged in user
     */
    fun getCurrentUser(): User = SecurityContextHolder.getContext().authentication.principal as User

    fun generateApproval(): Approval = Approval(getCurrentUser().id, OffsetDateTime.now())
}
