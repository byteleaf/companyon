package de.byteleaf.companyon

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.graphql.spring.boot.test.GraphQLResponse
import com.graphql.spring.boot.test.GraphQLTestSubscription
import com.graphql.spring.boot.test.GraphQLTestTemplate
import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.project.control.ProjectService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
abstract class AbstractIT(val gqlFolder: String) {

    @Autowired
    protected lateinit var companyService: CompanyService

    @Autowired
    protected lateinit var projectService: ProjectService

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var graphQLTestTemplate: GraphQLTestTemplate

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var graphQLTestSubscription: GraphQLTestSubscription

    protected fun performGQLByIdAndInput(gqlOperation: String, id: String, inputPayload: String): GraphQLResponse =
            performGQL(gqlOperation, "{ \"input\": $inputPayload, \"id\": \"$id\" }")

    protected fun performGQLByInput(gqlOperation: String, inputPayload: String): GraphQLResponse =
            performGQL(gqlOperation, "{ \"input\": $inputPayload }")

    protected fun performGQLById(gqlOperation: String, id: String): GraphQLResponse =
            performGQL(gqlOperation, "{ \"id\": \"$id\" }")


    protected fun performGQL(gqlOperation: String, payload: String? = null): GraphQLResponse =
            validateResponse(graphQLTestTemplate.perform(getGQLResource(gqlOperation), parseJSON(payload)))

    protected fun performGQLSubscription(gqlOperation: String, eventFunc: () -> Unit, payload: String? = null): GraphQLResponse {
        val firstResponse = graphQLTestSubscription.start(getGQLResource(gqlOperation))
        Executors.newScheduledThreadPool(1).schedule(eventFunc, 100, TimeUnit.MILLISECONDS)
        return validateResponse(firstResponse.awaitAndGetNextResponse(5000, true))
    }

    protected fun validateResponse(response: GraphQLResponse): GraphQLResponse {
        assertThat(response.isOk).isTrue()
        assertThat(response.readTree().hasNonNull("errors"))
                .describedAs("response has errors")
                .isFalse()
        return response
    }

    protected fun getGQLResource(gqlOperation: String): String = "graphql/$gqlFolder/$gqlOperation.graphql"

    protected fun parseJSON(payload: String? = null): ObjectNode? {
        if (payload != null) return objectMapper.readTree(payload) as ObjectNode?
        return null
    }

    protected fun clearDB() {
        companyService.deleteAll()
        projectService.deleteAll()
    }
}