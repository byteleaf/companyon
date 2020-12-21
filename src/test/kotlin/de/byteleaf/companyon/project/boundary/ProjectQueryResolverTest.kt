package de.byteleaf.companyon.project.boundary

import com.fasterxml.jackson.databind.ObjectMapper
import com.graphql.spring.boot.test.GraphQLTest
import com.graphql.spring.boot.test.GraphQLTestTemplate
import de.byteleaf.companyon.common.AbstractGraphQLTest
import de.byteleaf.companyon.company.entity.CompanyEntity
import de.byteleaf.companyon.project.entity.ProjectEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.doReturn
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@GraphQLTest
class ProjectQueryResolverTest : AbstractGraphQLTest() {
    @Autowired
    protected lateinit var graphQLTestTemplate: GraphQLTestTemplate

    @Test
    fun testGetProject() {
        val project = ProjectEntity("Companyon", CompanyEntity("byteleaf"))
        project.id = "1337"

        doReturn(Optional.of(project)).`when`(projectRepository).findById(eq(project.id!!))

        val variables = ObjectMapper().createObjectNode()
        variables.put("id", "${project.id}")

        val response = graphQLTestTemplate.perform("graphql/testGetProject.graphql", variables)

        assertThat(response.isOk).isTrue()
        assertThat(response.readTree().hasNonNull("errors"))
                .describedAs("response has errors")
                .isFalse()
        assertThat(response.get("$.data.project.id")).isNotNull()
        assertThat(response.get("$.data.project.name")).isEqualTo(project.name)
    }
}