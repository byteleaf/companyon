package de.byteleaf.companyon.user.auth

import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import java.time.Instant


@ActiveProfiles(profiles = ["test", "non-sec"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
// needed, otherwise embedded mongo db will produce a "Could not start process: <EOF>" after executing multiple tests in a row
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class OAuth2JwtAuthenticationConverterTest {

    @Autowired
    private lateinit var converter: OAuth2JwtAuthenticationConverter

    @Autowired
    private lateinit var userService: UserService

    @BeforeEach
    fun init() {
        userService.deleteAll()
    }

    @Test
    fun authenticateByOAuthSubject() {
        userService.create(UserInput("Jeff", "Bytezos", "jeff@byteleaf.de"), "test-subject")
        val userToken = converter.convert(JwtMock("test-subject"))
        Assertions.assertThat(userToken.principal.email).isEqualTo("jeff@byteleaf.de")
    }

}