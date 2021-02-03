package de.byteleaf.companyon.project

import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.logic.CompanyService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.ProjectGQLResponse
import de.byteleaf.companyon.project.dto.ProjectInput
import de.byteleaf.companyon.project.dto.ProjectUpdateGQLResponse
import de.byteleaf.companyon.project.entity.ProjectState
import de.byteleaf.companyon.project.logic.ProjectService
import de.byteleaf.companyon.test.AbstractIT
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


class ProjectIT : AbstractIT("project") {

    private val targetClass = ProjectGQLResponse::class.java

    @Autowired
    protected lateinit var companyService: CompanyService

    @Autowired
    protected lateinit var projectService: ProjectService

    @BeforeEach
    fun init() {
        projectService.deleteAll()
        companyService.deleteAll()
    }

    @Test
    fun getProjects() {
        seedTestProjects()
        val projects = performGQL("GetProjects").getList("$.data.projects", targetClass)
        Assertions.assertThat(projects.size).isEqualTo(2)
    }

    @Test
    fun getProjectsFilteredByCompany() {
        val projects = seedTestProjects()
        val companyId = projects[0].company
        val projectsFiltered =
            performGQL("GetProjects", "{ \"companies\": [\"$companyId\"] }").getList("$.data.projects", targetClass)
        Assertions.assertThat(projectsFiltered.size).isEqualTo(1)
    }

    @Test
    fun createProject() {
        val companyId = seedTestProjects()[0].company
        val createdProject = performGQLByInput("CreateProject", mapOf(Pair("name", "A"), Pair("company", companyId)))
            .get("$.data.createProject", targetClass)
        Assertions.assertThat(createdProject.name).isEqualTo("A")
        Assertions.assertThat(createdProject.state).isEqualTo(ProjectState.PLANNED)
        // Check if really existing
        val getResponse = performGQLById("GetProject", createdProject.id).get("$.data.project", targetClass)
        Assertions.assertThat(getResponse.name).isEqualTo("A")
    }

    /**
     * Get a single project and make sure the field resolver will be executed!
     */
    @Test
    fun getProject() {
        val getResponse = performGQLById("GetProject", seedTestProjects()[0].id!!).get("$.data.project", targetClass)
        Assertions.assertThat(getResponse.company!!.name).isEqualTo("Company A")
    }

    @Test
    fun deleteCompany() {
        val projects = seedTestProjects()
        Assertions.assertThat(projectService.findAll().size).isEqualTo(2)
        performGQLById("DeleteProject", projects[0].id!!)
        Assertions.assertThat(projectService.findAll().size).isEqualTo(1)
    }

    @Test
    fun updateProject() {
        val project = seedTestProjects()[0]
        val response = performGQLByIdAndInput(
            "UpdateProject",
            project.id!!, mapOf(Pair("name", "New name"), Pair("company", project.company))
        )
        val updatedCompany = response.get("$.data.updateProject", targetClass)
        Assertions.assertThat(updatedCompany.name).isEqualTo("New name")
    }

    @Test
    fun projectUpdatedSubscription() {
        val companyId = seedTestProjects()[0].company
        val projectUpdated = performGQLSubscription(
            "ProjectUpdateSubscription",
            { projectService.create(ProjectInput("New project", companyId)) })
            .get("$.data.projectUpdate", ProjectUpdateGQLResponse::class.java)
        Assertions.assertThat(projectUpdated.type).isEqualTo(EntityUpdateType.CREATED)
        Assertions.assertThat(projectUpdated.entity.name).isEqualTo("New project")
    }

    private fun seedTestProjects(): List<Project> {
        val p1 = projectService.create(
            ProjectInput(
                "Project A",
                companyService.create(CompanyInput("Company A")).id!!,
                ProjectState.IN_PROGRESS
            )
        )
        val p2 = projectService.create(ProjectInput("Project B", companyService.create(CompanyInput("Company B")).id!!))
        return listOf(p1, p2)
    }
}