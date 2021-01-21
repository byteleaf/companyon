package de.byteleaf.companyon.company

import de.byteleaf.companyon.test.AbstractIT
import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.error.ErrorCode
import de.byteleaf.companyon.company.logic.CompanyService
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.CompanyUpdate
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.project.logic.ProjectService
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.test.util.GQLErrorUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


class CompanyIT : AbstractIT("company") {

    private val targetClass = Company::class.java

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
    fun getCompanies() {
        seedTestCompany()
        val response = performGQL("GetCompanies")
        val companies = response.getList("$.data.companies", targetClass)
        assertThat(companies.size).isEqualTo(1)
    }

    @Test
    fun createCompany() {
        val createdCompany = performGQLByInput("CreateCompany", "{ \"name\": \"A\" }")
            .get("$.data.createCompany", targetClass)
        assertThat(createdCompany.name).isEqualTo("A")
        // Check if really existing
        val getResponse = performGQLById("GetCompany", createdCompany.id!!).get("$.data.company", targetClass)
        assertThat(getResponse.name).isEqualTo("A")
    }

    @Test
    fun deleteCompany() {
        val company = seedTestCompany()
        assertThat(projectService.findAll(listOf(company.id!!)).size).isEqualTo(2)
        performGQLById("DeleteCompany", company.id!!)
        // Make sure company is not existing anymore
        val response = performGQLById("GetCompany", company.id!!, true)
        GQLErrorUtil.expectError(response, ErrorCode.ENTITY_NOT_FOUND, EntityType.COMPANY, company.id!!)
        // Check if projects assigned to this company have been deleted too!
        assertThat(projectService.findAll(listOf(company.id!!)).size).isEqualTo(0)
    }

    @Test
    fun updateCompany() {
        val company = seedTestCompany()
        val response = performGQLByIdAndInput("UpdateCompany", company.id!!, "{ \"name\": \"New name\"}")
        val updatedCompany = response.get("$.data.updateCompany", targetClass)
        assertThat(updatedCompany.name).isEqualTo("New name")
    }

    @Test
    fun companyUpdatedSubscription() {
        val company = seedTestCompany()
        val companyUpdated =
            performGQLSubscription("CompanyUpdateSubscription", { companyService.delete(company.id!!) })
                .get("$.data.companyUpdate", CompanyUpdate::class.java)
        assertThat(companyUpdated.type).isEqualTo(EntityUpdateType.DELETED)
        assertThat(companyUpdated.entity!!.id).isEqualTo(company.id)
    }

    private fun seedTestCompany(): Company {
        val companyA = companyService.create(CompanyInput("Company A Ltd."))
        projectService.create(ProjectInput("Project A", companyA.id!!))
        projectService.create(ProjectInput("Project B", companyA.id!!))
        return companyA
    }
}