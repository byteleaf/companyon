package de.byteleaf.companyon.user.control

import de.byteleaf.companyon.auth.converter.OAuth2AuthenticationToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SecurityService {

    @Value("\${app.non-sec-oauth2-subject}")
    private lateinit var nonSecOAuth2Subject: String

    @Autowired
    private lateinit var env: Environment

    fun getCurrentAuth2Subject(): String {
        if(env.activeProfiles.contains("non-sec")) return nonSecOAuth2Subject

        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication !is OAuth2AuthenticationToken) {
            // TODO convert to graphql error
            throw object : AuthenticationException("Authentication is not an OAuth2 token") {}
        }
        return authentication.principal.oauth2Subject!!
    }
}