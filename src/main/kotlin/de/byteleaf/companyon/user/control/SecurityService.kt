package de.byteleaf.companyon.user.control

import de.byteleaf.companyon.common.auth.OAuth2AuthenticationToken
import de.byteleaf.companyon.user.dto.User
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SecurityService {

    fun getPrincipal(): User {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication !is OAuth2AuthenticationToken) {
            // TODO convert to graphql error
            throw object : AuthenticationException("Authentication is not an OAuth2 token") {}
        }

        return authentication.principal
    }
}