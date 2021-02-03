package de.byteleaf.companyon.user

import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.test.AbstractIT
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.UserUpdate
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.logic.UserService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestPropertySource


@TestPropertySource(properties = ["app.non-sec-user-admin=true"])
class UserIT : AbstractIT("user") {

    private val targetClass = User::class.java

    @Autowired
    protected lateinit var userService: UserService

    @BeforeEach
    fun init() {
        userService.deleteAll()
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
    fun deleteUser() {
        val createdUser = seedTestUser()
        Assertions.assertThat(userService.findAll().size).isEqualTo(1)
        performGQLById("DeleteUser", createdUser.id!!)
        Assertions.assertThat(userService.findAll().size).isEqualTo(0)
    }

    @Test
    fun updateUser() {
        val user = seedTestUser()
        val updatedEntity = performGQLByIdAndInput("UpdateUser", user.id!!,
            mapOf(Pair("email", "jeff@byteleaf.de"), Pair("firstName", "a"), Pair("lastName", "b")))
            .get("$.data.updateUser", targetClass)
        Assertions.assertThat(updatedEntity.email).isEqualTo("jeff@byteleaf.de")
    }

    @Test
    fun createUser() {
        val createdEntity = performGQLByInput("CreateUser", mapOf(Pair("email", "jeff@byteleaf.de"), Pair("firstName", "a"), Pair("lastName", "b")))
            .get("$.data.createUser",targetClass)
        Assertions.assertThat(createdEntity.email).isEqualTo("jeff@byteleaf.de")
        // Check if really existing
        val getResponse = performGQLById("GetUser", createdEntity.id!!).get("$.data.user", targetClass)
        Assertions.assertThat(getResponse.firstName).isEqualTo("a")
    }

    @Test
    fun updatedSubscription() {
        val user = seedTestUser()
        val projectUpdated = performGQLSubscription("UserUpdateSubscription", { userService.update(user.id!!, UserInput("a", "b", "c", false)) }
        ).get("$.data.userUpdate", UserUpdate::class.java)
        Assertions.assertThat(projectUpdated.type).isEqualTo(EntityUpdateType.UPDATED)
        Assertions.assertThat(projectUpdated.entity!!.lastName).isEqualTo("b")
    }

    private fun seedTestUser(): User =
        userService.create(UserInput("Hans", "Bytezos", "hans@byteleaf.de", false))
}