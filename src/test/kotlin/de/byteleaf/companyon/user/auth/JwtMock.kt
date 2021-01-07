package de.byteleaf.companyon.user.auth
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties

class JwtMock(val subjectMock: String): Jwt("a", null, null, mapOf(Pair("a", "b")), mapOf(Pair("a", "b"))) {


    override fun getClaimAsString(claim: String): String = subjectMock

}