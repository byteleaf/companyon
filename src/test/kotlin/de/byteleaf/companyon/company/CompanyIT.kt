package de.byteleaf.companyon.company

import de.byteleaf.companyon.AbstractIT
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.project.dto.input.ProjectInput
import graphql.ExecutionResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class CompanyIT : AbstractIT("company") {

    @BeforeEach
    fun init() {
        clearDB()
    }

    @Test
    fun getCompanies() {
        seedTestCompany()
        val response = performGQL("GetCompanies")
        val companies = response.getList("$.data.companies", Company::class.java)
        assertThat(companies.size).isEqualTo(1)
    }

    @Test
    fun createCompany() {
        val createdCompany = performGQLByInput("CreateCompany", "{ \"name\": \"A\" }")
                .get("$.data.createCompany", Company::class.java)
        assertThat(createdCompany.name).isEqualTo("A")
        // Check if really existing
        val getResponse = performGQLById("GetCompany", createdCompany.id!!).get("$.data.company", Company::class.java)
        assertThat(getResponse.name).isEqualTo("A")
    }

    @Test
    fun deleteCompany() {
        val company = seedTestCompany()
        assertThat(projectService.findByCompany(company.id!!).size).isEqualTo(2)
        performGQLById("DeleteCompany", company.id!!)
        // Make sure company is not existing anymore
        val response = graphQLTestTemplate.perform(getGQLResource("GetCompany"), parseJSON("{ \"id\": \"${company.id}\" }"))
        assertThat(response.get("$.errors[0].extensions.entityId")).isEqualTo(company.id)
        assertThat(response.get("$.errors[0].extensions.entityType")).isEqualTo("COMPANY")
        assertThat(response.get("$.errors[0].extensions.code")).isEqualTo("ENTITY_NOT_FOUND")
        // Check if projects assigned to this company have been deleted too!
        assertThat(projectService.findByCompany(company.id!!).size).isEqualTo(0)
    }

    @Test
    fun updateCompany() {
        val company = seedTestCompany()
        val response = performGQLByIdAndInput("UpdateCompany", company.id!!, "{ \"name\": \"New name\"}")
        val updatedCompany = response.get("$.data.updateCompany", Company::class.java)
        assertThat(updatedCompany.name).isEqualTo("New name")
    }

    @Test
    fun testSubscriptions() {
        val company = seedTestCompany()
        val response = graphQLTestSubscription.start(getGQLResource("CompanyUpdatedSubscription"))
        // TODO delete company
        val response2 = response.awaitAndGetNextResponse(5000, true)
        val rr = 99
    }

    private fun seedTestCompany(): Company {
        val companyA = companyService.create(CompanyInput("Company A Ltd."))
        projectService.create(ProjectInput("Project A", companyA.id!!))
        projectService.create(ProjectInput("Project B", companyA.id!!))
        return companyA
    }
}