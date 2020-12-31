package de.byteleaf.companyon.project

import de.byteleaf.companyon.AbstractIT
import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.CompanyUpdated
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
        val companyId = projects.get(0).company.id
        val projectsFiltered = performGQL("GetProjects", "{ \"companies\": [\"$companyId\"] }").getList("$.data.projects", Project::class.java)
        Assertions.assertThat(projectsFiltered.size).isEqualTo(1)
    }

//    @Test
//    fun createCompany() {
//        val createdCompany = performGQLByInput("CreateCompany", "{ \"name\": \"A\" }")
//                .get("$.data.createCompany", Company::class.java)
//        Assertions.assertThat(createdCompany.name).isEqualTo("A")
//        // Check if really existing
//        val getResponse = performGQLById("GetCompany", createdCompany.id!!).get("$.data.company", Company::class.java)
//        Assertions.assertThat(getResponse.name).isEqualTo("A")
//    }
//
//    @Test
//    fun deleteCompany() {
//        val company = seedTestCompany()
//        Assertions.assertThat(projectService.findAll(listOf(company.id!!)).size).isEqualTo(2)
//        performGQLById("DeleteCompany", company.id!!)
//        // Make sure company is not existing anymore
//        val response = graphQLTestTemplate.perform(getGQLResource("GetCompany"), parseJSON("{ \"id\": \"${company.id}\" }"))
//        Assertions.assertThat(response.get("$.errors[0].extensions.entityId")).isEqualTo(company.id)
//        Assertions.assertThat(response.get("$.errors[0].extensions.entityType")).isEqualTo("COMPANY")
//        Assertions.assertThat(response.get("$.errors[0].extensions.code")).isEqualTo("ENTITY_NOT_FOUND")
//        // Check if projects assigned to this company have been deleted too!
//        Assertions.assertThat(projectService.findAll(listOf(company.id!!)).size).isEqualTo(0)
//    }
//
//    @Test
//    fun updateCompany() {
//        val company = seedTestCompany()
//        val response = performGQLByIdAndInput("UpdateCompany", company.id!!, "{ \"name\": \"New name\"}")
//        val updatedCompany = response.get("$.data.updateCompany", Company::class.java)
//        Assertions.assertThat(updatedCompany.name).isEqualTo("New name")
//    }
//
//    @Test
//    fun companyUpdatedSubscription() {
//        val company = seedTestCompany()
//        val companyUpdated = performGQLSubscription("CompanyUpdatedSubscription", { companyService.delete(company.id!!) })
//                .get("$.data.companyUpdated", CompanyUpdated::class.java)
//        Assertions.assertThat(companyUpdated.type).isEqualTo(EntityUpdateType.DELETED)
//        Assertions.assertThat(companyUpdated.entity!!.id).isEqualTo(company.id)
//    }

    private fun seedTestProjects(): List<Project> {
        val p1 = projectService.create(ProjectInput("Project A", companyService.create(CompanyInput("Company A")).id!!, ProjectState.RUNNING))
        val p2 = projectService.create(ProjectInput("Project B", companyService.create(CompanyInput("Company B")).id!!))
        return listOf(p1, p2)
    }
}