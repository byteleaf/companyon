package de.byteleaf.companyon.auth.oauth

import de.byteleaf.companyon.auth.control.AuthInfoService
import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.input.UserInput
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test", "non-sec"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
// needed, otherwise embedded mongo db will produce a "Could not start process: <EOF>" after executing multiple tests in a row
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class OAuth2JwtAuthenticationConverterTest {


    @MockBean
    private lateinit var authInfoService: AuthInfoService

    @Autowired
    private lateinit var converter: OAuth2JwtAuthenticationConverter

    @Autowired
    private lateinit var userService: UserService

    @BeforeEach
    fun init() {
        userService.deleteAll()
        Mockito.`when`(authInfoService.loadUserInfo(ArgumentMatchers.anyString())).thenReturn(mapOf(Pair("email", "JEFF@byteleaf.de")))
    }

    @Test
    fun authenticateByOAuthSubject() {
        userService.create(UserInput("Jeff", "Bytezos", "jeff@byteleaf.de", true), "test-subject")
        val userToken = converter.convert(JwtMock("test-subject"))
        Assertions.assertThat(userToken.principal.email).isEqualTo("jeff@byteleaf.de")
    }

    @Test
    fun activateNewUser() {
        userService.create(UserInput("Jeff", "Bytezos", "jeff@byteleaf.de", true))
        val userToken = converter.convert(JwtMock("test-subject"))
        Assertions.assertThat(userToken.principal.email).isEqualTo("jeff@byteleaf.de")
        // Check if the subject was updated
        val updatedUser = userService.findByOAuth2Subject("test-subject")
        Assertions.assertThat(updatedUser!!.email).isEqualTo("jeff@byteleaf.de")
    }

    @Test
    fun activateNewUserEmailNotFound() {
        assertThrows<UsernameNotFoundException> {converter.convert(JwtMock("test-subject"))  }
    }

    @Test
    fun loginAsExistingUser() {
        userService.create(UserInput("Jeff", "Bytezos", "jeff@byteleaf.de", true), "test-subject")
        assertThrows<InsufficientAuthenticationException> {converter.convert(JwtMock("other-subject"))  }
    }
}