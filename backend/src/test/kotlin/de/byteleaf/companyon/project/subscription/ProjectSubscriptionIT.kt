package de.byteleaf.companyon.project.subscription

import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.logic.CompanyService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.ProjectInput
import de.byteleaf.companyon.project.dto.ProjectUpdateGQLResponse
import de.byteleaf.companyon.project.entity.ProjectState
import de.byteleaf.companyon.project.logic.ProjectService
import de.byteleaf.companyon.test.AbstractSubscriptionIT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ProjectSubscriptionIT : AbstractSubscriptionIT("project") {

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
    fun projectUpdatedSubscription() {
        val companyId = seedTestProjects()[0].company
        val projectUpdated = performGQLSubscription(
            "ProjectUpdateSubscription",
            { projectService.create(ProjectInput("New project", companyId)) }
        )
            .get("$.data.projectUpdate", ProjectUpdateGQLResponse::class.java)
        assertThat(projectUpdated.type).isEqualTo(EntityUpdateType.CREATED)
        assertThat(projectUpdated.entity.name).isEqualTo("New project")
    }

    private fun seedTestProjects(): List<Project> {
        val p1 = projectService.create(
            ProjectInput(
                "Project A",
                companyService.create(CompanyInput("Company A")).id,
                ProjectState.IN_PROGRESS
            )
        )
        val p2 = projectService.create(ProjectInput("Project B", companyService.create(CompanyInput("Company B")).id))
        return listOf(p1, p2)
    }
}
