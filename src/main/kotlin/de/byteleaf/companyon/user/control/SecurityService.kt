package de.byteleaf.companyon.user.control

import de.byteleaf.companyon.common.auth.OAuth2AuthenticationToken
import de.byteleaf.companyon.user.dto.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SecurityService {

    @Value("\${app.non-sec-oauth2-subject}")
    private lateinit var nonSecOAuth2Subject: String

    @Value("\${spring.profiles.active}")
    private lateinit var activeProfile: String

    fun getCurrentAuth2Subject(): String {
        if(listOf("test", "non-sec").contains(activeProfile)) return nonSecOAuth2Subject

        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication !is OAuth2AuthenticationToken) {
            // TODO convert to graphql error
            throw object : AuthenticationException("Authentication is not an OAuth2 token") {}
        }
        return authentication.principal.oAuth2Subject!!
    }
}