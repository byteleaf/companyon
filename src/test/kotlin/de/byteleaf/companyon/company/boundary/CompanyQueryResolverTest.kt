package de.byteleaf.companyon.company.boundary

import com.fasterxml.jackson.databind.ObjectMapper
import com.graphql.spring.boot.test.GraphQLTest
import com.graphql.spring.boot.test.GraphQLTestTemplate
import de.byteleaf.companyon.common.AbstractGraphQLTest
import de.byteleaf.companyon.company.entity.CompanyEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.doReturn
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@GraphQLTest
class CompanyQueryResolverTest : AbstractGraphQLTest() {
//    @Autowired
//    protected lateinit var graphQLTestTemplate: GraphQLTestTemplate
//
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
//        val response = graphQLTestTemplate.perform("graphql/testGetCompany.graphql", variables)
//
//        assertThat(response.isOk).isTrue()
//        assertThat(response.readTree().hasNonNull("errors"))
//                .describedAs("response has errors")
//                .isFalse()
//        assertThat(response.get("$.data.company.id")).isNotNull()
//        assertThat(response.get("$.data.company.name")).isEqualTo(company.name)
//    }
}