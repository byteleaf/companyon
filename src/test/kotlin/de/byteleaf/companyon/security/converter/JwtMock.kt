package de.byteleaf.companyon.security.converter
import org.springframework.security.oauth2.jwt.Jwt

/**
 * The only important parameter is the [subjectMock], everything else won't be used is just there to keep the tests running,
 * empty maps are not working!
 */
class JwtMock(val subjectMock: String): Jwt("a", null, null, mapOf(Pair("a", "b")), mapOf(Pair("a", "b"))) {


    override fun getClaimAsString(claim: String): String = subjectMock

}