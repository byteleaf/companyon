package de.byteleaf.companyon.company.subscription

import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.CompanyUpdate
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.logic.CompanyService
import de.byteleaf.companyon.project.dto.ProjectInput
import de.byteleaf.companyon.project.dto.ProjectUpdateGQLResponse
import de.byteleaf.companyon.project.logic.ProjectService
import de.byteleaf.companyon.test.AbstractSubscriptionIT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class CompanySubscriptionIT: AbstractSubscriptionIT("company") {

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
    fun deleteCompanyTestProjectSubscription() {
        val company = seedTestCompany()
        val projectUpdated = performGQLSubscription("project/ProjectUpdateSubscription", { companyService.delete(company.id!!) })
            .get("$.data.projectUpdate", ProjectUpdateGQLResponse::class.java)
        assertThat(projectUpdated.type).isEqualTo(EntityUpdateType.DELETED)
        assertThat(projectUpdated.entity.name).isEqualTo("Project A")
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