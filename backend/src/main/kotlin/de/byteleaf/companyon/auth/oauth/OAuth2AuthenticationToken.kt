package de.byteleaf.companyon.auth.oauth

import de.byteleaf.companyon.user.dto.output.User
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class OAuth2AuthenticationToken private constructor(
    private val principal: User,
    authorities: Collection<GrantedAuthority>?
) : AbstractAuthenticationToken(authorities) {

    override fun getCredentials(): Any {
        return Any()
    }

    override fun getPrincipal(): User {
        return principal
    }

    companion object {
        internal fun create(user: User, details: Any?): OAuth2AuthenticationToken {
            val result = OAuth2AuthenticationToken(user, user.getRoles())
            result.isAuthenticated = true
            result.details = details
            return result
        }
    }
}
