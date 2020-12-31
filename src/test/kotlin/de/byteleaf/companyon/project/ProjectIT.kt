package de.byteleaf.companyon.project

import de.byteleaf.companyon.AbstractIT
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.project.entity.ProjectState
import de.byteleaf.companyon.project.respository.ProjectRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ProjectIT : AbstractIT("project") {

    @BeforeEach
    fun init() {
        clearDB()
    }

    @Autowired
    protected lateinit var repository: ProjectRepository

    @Test
    fun getProjects() {
        seedTestProjects()
        val projects = performGQL("GetProjects").getList("$.data.projects", Project::class.java)
        Assertions.assertThat(projects.size).isEqualTo(2)
        // Filtered
     //   projects.get(0).company.id


    }

    private fun seedTestProjects(): List<Project> {
        val p1 = projectService.create(ProjectInput("Project A", companyService.create(CompanyInput("Company A")).id!!, ProjectState.RUNNING))
        val p2 = projectService.create(ProjectInput("Project B", companyService.create(CompanyInput("Company B")).id!!))
        return listOf(p1, p2)
    }
}