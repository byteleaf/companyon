package de.byteleaf.companyon.company


import de.byteleaf.companyon.AbstractIntegrationTest
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.entity.CompanyEntity
import de.byteleaf.companyon.project.dto.input.ProjectInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class CompanyIntegrationTest : AbstractIntegrationTest("company") {

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
    fun createAndGetCompany() {
        val createdCompany = performGQL("CreateCompany", "{ \"input\": { \"name\": \"A\" }}")
                .get("$.data.createCompany", Company::class.java)
        assertThat(createdCompany.name).isEqualTo("A")
        // Get
        val getResponse = performGQL("GetCompany", "{ \"id\": \"${createdCompany.id}\" }")
                .get("$.data.company", Company::class.java)
        assertThat(getResponse.name).isEqualTo("A")
    }

    @Test
    fun deleteCompany() {
        val company = seedTestCompany()
        performGQL("DeleteCompany", "{ \"id\": \"${company.id}\" }")
        val response = graphQLTestTemplate.perform(getGQLResource("GetCompany"), parseJSON("{ \"id\": \"${company.id}\" }"))
        //assertThat(response.errors).isEqualTo("A")
        val a = 0;
    }

    @Test
    fun updateCompany() {
        val response = performGQL("GetCompany", "{ \"id\": \"\" }")
        val createdCompany = response.get("$.data.createCompany", Company::class.java)
        assertThat(createdCompany.name).isEqualTo("A")
    }

//    @Test
//    fun testGetCompany() {
//        val company = CompanyEntity("Byteleaf")
//        company.id = "1337"
//
//        doReturn(Optional.of(company)).`when`(companyRepository).findById(eq(company.id!!))
//
//        val variables = ObjectMapper().createObjectNode()
//        variables.put("id", "${company.id}")
//
//        val response = graphQLTestTemplate.perform("graphql/GetCompany.graphql", variables)
//
//        assertThat(response.isOk).isTrue()
//        assertThat(response.readTree().hasNonNull("errors"))
//                .describedAs("response has errors")
//                .isFalse()
//        assertThat(response.get("$.data.company.id")).isNotNull()
//        assertThat(response.get("$.data.company.name")).isEqualTo(company.name)
//    }


    private fun seedTestCompany(): Company {
        val companyA = companyService.create(CompanyInput("Company A Ltd."))
        projectService.create(ProjectInput("Project A", companyA.id!!))
        projectService.create(ProjectInput("Project B", companyA.id!!))
        return companyA
    }
}