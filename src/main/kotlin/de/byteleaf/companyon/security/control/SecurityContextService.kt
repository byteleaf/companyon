package de.byteleaf.companyon.security.control

import de.byteleaf.companyon.common.error.exception.FatalException
import de.byteleaf.companyon.security.converter.OAuth2AuthenticationToken
import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SecurityContextService {

    @Value("\${app.non-sec-oauth2-subject}")
    private lateinit var nonSecOAuth2Subject: String

    @Autowired
    private lateinit var env: Environment

    @Autowired
    private lateinit var userService: UserService

    /**
     * To get the current logged in user
     */
    fun getCurrentUser(): User {
        if(env.activeProfiles.contains("non-sec")) return userService.findByOauth2Subject(nonSecOAuth2Subject)!!

        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication !is OAuth2AuthenticationToken) {
            throw FatalException("Authentication is not an OAuth2 token")
        }
        return authentication.principal
    }
}