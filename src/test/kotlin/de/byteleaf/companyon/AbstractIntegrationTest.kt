package de.byteleaf.companyon

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.graphql.spring.boot.test.GraphQLResponse
import com.graphql.spring.boot.test.GraphQLTestTemplate
import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.project.dto.input.ProjectInput
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

open class AbstractIntegrationTest(val gqlFolder: String) {

    @Autowired
    protected lateinit var companyService: CompanyService

    @Autowired
    protected lateinit var projectService: ProjectService

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private lateinit var graphQLTestTemplate: GraphQLTestTemplate

    protected fun performGQL(gqlOperation: String, variables: ObjectNode? = null): GraphQLResponse {
        val response = graphQLTestTemplate.perform("graphql/$gqlFolder/$gqlOperation.graphql", variables)
        assertThat(response.isOk).isTrue()
        assertThat(response.readTree().hasNonNull("errors"))
                .describedAs("response has errors")
                .isFalse()
        return response
    }

    protected fun restTestData() {
        companyService.deleteAll()
        val companyA = companyService.create(CompanyInput("Company A Ltd."))
        val companyB = companyService.create(CompanyInput("Company B Ltd."))

        projectService.deleteAll()
        projectService.create(ProjectInput("Project A", companyA.id!!))
        projectService.create(ProjectInput("Project B", companyA.id!!))

        projectService.create(ProjectInput("Project C", companyB.id!!))
        projectService.create(ProjectInput("Project D", companyB.id!!))
    }
}