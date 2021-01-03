package de.byteleaf.companyon.project

import de.byteleaf.companyon.AbstractIT
import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.error.ErrorCode
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.ProjectUpdate
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.project.entity.ProjectState
import de.byteleaf.companyon.project.respository.ProjectRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ProjectIT : AbstractIT("project") {

    private val targetClass = Project::class.java

    @BeforeEach
    fun init() {
        clearDB()
    }

    @Autowired
    protected lateinit var repository: ProjectRepository

    @Test
    fun getProjects() {
        seedTestProjects()
        val projects = performGQL("GetProjects").getList("$.data.projects", targetClass)
        Assertions.assertThat(projects.size).isEqualTo(2)
        // Filtered by company
        val companyId = projects.get(0).company.id
        val projectsFiltered = performGQL("GetProjects", "{ \"companies\": [\"$companyId\"] }").getList("$.data.projects", targetClass)
        Assertions.assertThat(projectsFiltered.size).isEqualTo(1)
    }

    @Test
    fun createProject() {
        val companyId = seedTestProjects().get(0).company.id
        val createdProject = performGQLByInput("CreateProject", "{ \"name\": \"A\", \"company\":\"$companyId\" }")
                .get("$.data.createProject",targetClass)
        Assertions.assertThat(createdProject.name).isEqualTo("A")
        Assertions.assertThat(createdProject.state).isEqualTo(ProjectState.PLANNED)
        // Check if really existing
        val getResponse = performGQLById("GetProject", createdProject.id!!).get("$.data.project", targetClass)
        Assertions.assertThat(getResponse.name).isEqualTo("A")
    }

    @Test
    fun createProjectWithNotExistingCompany() {
        val response = performGQLByInput("CreateProject", "{ \"name\": \"A\", \"company\":\"INVALID\" }", true)
        expectError(response, ErrorCode.ENTITY_NOT_FOUND, EntityType.COMPANY, "INVALID")
    }

    @Test
    fun deleteCompany() {
        val projects = seedTestProjects()
        Assertions.assertThat(projectService.findAll().size).isEqualTo(2)
        performGQLById("DeleteProject", projects.get(0).id!!)
        Assertions.assertThat(projectService.findAll().size).isEqualTo(1)
    }

    @Test
    fun updateProject() {
        val project = seedTestProjects().get(0)
        val response = performGQLByIdAndInput("UpdateProject", project.id!!, "{ \"name\": \"New name\", \"company\":\"${project.company.id}\" }")
        val updatedCompany = response.get("$.data.updateProject", targetClass)
        Assertions.assertThat(updatedCompany.name).isEqualTo("New name")
    }

    @Test
    fun companyUpdatedSubscription() {
        val companyId = seedTestProjects().get(0).company.id!!
        val projectUpdated = performGQLSubscription("ProjectUpdateSubscription", { projectService.create(ProjectInput("New project", companyId)) })
            .get("$.data.projectUpdate", ProjectUpdate::class.java)
        Assertions.assertThat(projectUpdated.type).isEqualTo(EntityUpdateType.CREATED)
        Assertions.assertThat(projectUpdated.entity!!.name).isEqualTo("New project")
    }

    private fun seedTestProjects(): List<Project> {
        val p1 = projectService.create(ProjectInput("Project A", companyService.create(CompanyInput("Company A")).id!!, ProjectState.RUNNING))
        val p2 = projectService.create(ProjectInput("Project B", companyService.create(CompanyInput("Company B")).id!!))
        return listOf(p1, p2)
    }
}