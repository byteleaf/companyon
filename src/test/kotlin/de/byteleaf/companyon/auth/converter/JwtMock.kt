package de.byteleaf.companyon.auth.converter
import org.springframework.security.oauth2.jwt.Jwt

class JwtMock(val subjectMock: String): Jwt("a", null, null, mapOf(Pair("a", "b")), mapOf(Pair("a", "b"))) {


    override fun getClaimAsString(claim: String): String = subjectMock

}