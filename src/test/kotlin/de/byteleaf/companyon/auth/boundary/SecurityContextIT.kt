package de.byteleaf.companyon.auth.boundary

import de.byteleaf.companyon.AbstractIT
import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value


class SecurityContextIT : AbstractIT("security") {


    @Value("\${app.non-sec-oauth2-subject}")
    private lateinit var nonSecOAuth2Subject: String

    @Autowired
    protected lateinit var userService: UserService

    @BeforeEach
    fun init() {
        userService.deleteAll()
    }

    @Test
    fun getCurrentUser() {
        userService.create(UserInput("Jeff", "Bytezos", "jeff@byteleaf.de", true), nonSecOAuth2Subject)
        val response = performGQL("GetCurrentUser").get("$.data.currentUser", User::class.java)
        Assertions.assertThat(response.email).isEqualTo("jeff@byteleaf.de")
    }
}