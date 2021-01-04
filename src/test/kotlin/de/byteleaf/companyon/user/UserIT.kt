package de.byteleaf.companyon.user

import de.byteleaf.companyon.AbstractIT
import de.byteleaf.companyon.user.control.SecurityService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.mock.mockito.MockBean

class UserIT : AbstractIT("user") {

    private val targetClass = User::class.java

    @MockBean
    private lateinit var securityService: SecurityService

    @BeforeEach
    fun init() {
        mockCurrentUser()
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
        val response = performGQL("GetCurrentUser").get("$.data.currentUser", targetClass)
        Assertions.assertThat(response.email).isEqualTo("josef@byteleaf.de")
    }

//
//    @Test
//    fun createProjectWithNotExistingCompany() {
//        val response = performGQLByInput("CreateProject", "{ \"name\": \"A\", \"company\":\"INVALID\" }", true)
//        expectError(response, ErrorCode.ENTITY_NOT_FOUND, EntityType.COMPANY, "INVALID")
//    }
//
//    @Test
//    fun deleteCompany() {
//        val projects = seedTestProjects()
//        Assertions.assertThat(projectService.findAll().size).isEqualTo(2)
//        performGQLById("DeleteProject", projects.get(0).id!!)
//        Assertions.assertThat(projectService.findAll().size).isEqualTo(1)
//    }
//
//    @Test
//    fun updateProject() {
//        val project = seedTestProjects().get(0)
//        val response = performGQLByIdAndInput("UpdateProject", project.id!!, "{ \"name\": \"New name\", \"company\":\"${project.company.id}\" }")
//        val updatedCompany = response.get("$.data.updateProject", targetClass)
//        Assertions.assertThat(updatedCompany.name).isEqualTo("New name")
//    }
//
//    @Test
//    fun companyUpdatedSubscription() {
//        val companyId = seedTestProjects().get(0).company.id!!
//        val projectUpdated = performGQLSubscription("ProjectUpdateSubscription", { projectService.create(ProjectInput("New project", companyId)) })
//            .get("$.data.projectUpdate", ProjectUpdate::class.java)
//        Assertions.assertThat(projectUpdated.type).isEqualTo(EntityUpdateType.CREATED)
//        Assertions.assertThat(projectUpdated.entity!!.name).isEqualTo("New project")
//    }
//
    private fun mockCurrentUser() {
        val user = User("Joseph", "Bytezos", "josef@byteleaf.de", null, null)
        user.id = "test-id"
        Mockito.`when`(securityService.getPrincipal()).thenReturn(user)
    }

    private fun seedTestUser(): User =
        userService.create(UserInput("oauth2Subject1", "Hans", "Bytezos", "hans@byteleaf.de"))
}