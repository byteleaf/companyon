package de.byteleaf.companyon.user

import de.byteleaf.companyon.test.AbstractQueryMutationIT
import de.byteleaf.companyon.user.constant.Country
import de.byteleaf.companyon.user.constant.ProvinceGermany
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.UserGQLResponse
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.logic.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = ["app.non-sec-user-admin=true"])
class UserAdminIT : AbstractQueryMutationIT("user") {

    private val targetClass = UserGQLResponse::class.java

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
        assertThat(projects.size).isEqualTo(1)
    }

    @Test
    fun getUser() {
        val createdUser = seedTestUser()
        val response = performGQLById("GetUser", createdUser.id).get("$.data.user", targetClass)
        assertThat(response.email).isEqualTo("hans@byteleaf.de")
    }

    @Test
    fun deleteUser() {
        val createdUser = seedTestUser()
        assertThat(userService.findAll().size).isEqualTo(1)
        performGQLById("DeleteUser", createdUser.id)
        assertThat(userService.findAll().size).isEqualTo(0)
    }

    @Test
    fun updateUser() {
        val user = seedTestUser()
        val updatedEntity = performGQLByIdAndInput(
            "UpdateUser", user.id,
            mapOf(Pair("email", "jeff@byteleaf.de"), Pair("firstName", "a"), Pair("lastName", "b"))
        )
            .get("$.data.updateUser", targetClass)
        assertThat(updatedEntity.email).isEqualTo("jeff@byteleaf.de")
    }

    @Test
    fun createUser() {
        val createdEntity = performGQLByInput("CreateUser", mapOf(Pair("email", "jeff@byteleaf.de"), Pair("firstName", "a"), Pair("lastName", "b")))
            .get("$.data.createUser", targetClass)
        assertThat(createdEntity.email).isEqualTo("jeff@byteleaf.de")
        // Check if really existing
        val getResponse = performGQLById("GetUser", createdEntity.id!!).get("$.data.user", targetClass)
        assertThat(getResponse.firstName).isEqualTo("a")
        assertThat(getResponse.vacationDaysPerYear).isEqualTo(30)
        assertThat(getResponse.weeklyWorkingMinutes).isEqualTo(2400)
        assertThat(getResponse.country).isEqualTo(Country.GERMANY)
        assertThat(getResponse.province).isEqualTo(ProvinceGermany.BAYERN)
    }

    private fun seedTestUser(): User =
        userService.create(UserInput("Hans", "Bytezos", "hans@byteleaf.de", false))
}
