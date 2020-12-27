package de.byteleaf.companyon.company


import de.byteleaf.companyon.AbstractIntegrationTest
import de.byteleaf.companyon.company.dto.Company
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
class CompanyIntegrationTest : AbstractIntegrationTest("company") {

    @BeforeEach
    fun init() {
        restTestData()
    }

    @Test
    fun getCompanies() {
        val response = performGQL("GetCompanies")
        val companies = response.getList("$.data.companies", Company::class.java)
        assertThat(companies.size).isEqualTo(2)
    }

    @Test
    fun createCompany() {
        // TODO
//        val response = performGQL("GetCompanies")
//        val companies = response.getList("$.data.companies", Company::class.java)
//        assertThat(companies.size).isEqualTo(2)
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
}