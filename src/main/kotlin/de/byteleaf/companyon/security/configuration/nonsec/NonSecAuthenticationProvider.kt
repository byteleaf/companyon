package de.byteleaf.companyon.security.configuration.nonsec

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component

import java.util.ArrayList



@Component
class NonSecAuthenticationProvider : AuthenticationProvider {


    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication? {
//        val name: String = authentication.getName()
//        val password: String = authentication.getCredentials().toString()
//        return if (shouldAuthenticateAgainstThirdPartySystem()) {
//
//            // use the credentials
//            // and authenticate against the third-party system
//            UsernamePasswordAuthenticationToken(
//                name, password, ArrayList()
//            )
//        } else {
//            null
//        }
        return null
    }

    override fun supports(p0: Class<*>?): Boolean {
        TODO("Not yet implemented")
    }

}