package de.byteleaf.companyon.user.control

import de.byteleaf.companyon.common.auth.OAuth2AuthenticationToken
import de.byteleaf.companyon.common.control.AbstractDataService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.entity.UserEntity
import de.byteleaf.companyon.user.repository.UserRepository
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class UserService : AbstractDataService<UserEntity, User, UserInput, UserRepository>() {

    /**
     * To get the current logged in user
     */
    fun getCurrentUser(): User {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication !is OAuth2AuthenticationToken) {
            throw object : AuthenticationException("authentication is not an OAuth2 token") {}
        }

        return authentication.principal
    }

    fun byOAuth2Subject(oauth2Subject: String): User? {
        val result = repository.findByOauth2Subject(oauth2Subject) ?: return null

        return entityToOutput(result)
    }
}
