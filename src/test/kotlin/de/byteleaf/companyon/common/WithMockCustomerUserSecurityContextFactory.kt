package de.byteleaf.companyon.common

import de.byteleaf.companyon.common.auth.OAuth2AuthenticationToken
import de.byteleaf.companyon.user.dto.User
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContextFactory


class WithMockCustomerUserSecurityContextFactory : WithSecurityContextFactory<WithMockCustomUser> {
    override fun createSecurityContext(annotation: WithMockCustomUser?): SecurityContext {
        val context = SecurityContextHolder.createEmptyContext()

        if (annotation != null) {
            val user = User(
                annotation.firstName,
                annotation.lastName,
                annotation.email,
                null, null
            )
            val authentication = OAuth2AuthenticationToken.create(user, emptyMap<Any, String>())

            context.authentication = authentication
        }

        return context
    }
}