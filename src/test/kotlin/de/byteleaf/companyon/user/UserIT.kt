package de.byteleaf.companyon.user

import de.byteleaf.companyon.AbstractIT
import de.byteleaf.companyon.user.control.SecurityService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.mock.mockito.MockBean

class UserIT : AbstractIT("user") {

    private val targetClass = User::class.java

    @Value("\${app.non-sec-oauth2-subject}")
    private lateinit var nonSecOAuth2Subject: String

    @BeforeEach
    fun init() {
        clearDB()
    }

    @Test
    fun getUsers() {
        seedTestUser()
        val projects = performGQL("GetUsers").getList("$.data.users", targetClass)
        Assertions.assertThat(projects.size).isEqualTo(1)
    }

    @Test
    fun getUser() {
        val createdUser = seedTestUser()
        val response = performGQLById("GetUser", createdUser.id!!).get("$.data.user", targetClass)
        Assertions.assertThat(response.email).isEqualTo("hans@byteleaf.de")
    }

    @Test
    fun getCurrentUser() {
        userService.create(UserInput("Jeff", "Bytezos", "jeff@byteleaf.de"), nonSecOAuth2Subject)
        val response = performGQL("GetCurrentUser").get("$.data.currentUser", targetClass)
        Assertions.assertThat(response.email).isEqualTo("jeff@byteleaf.de")
    }

    private fun seedTestUser(): User =
        userService.create(UserInput("Hans", "Bytezos", "hans@byteleaf.de"))
}