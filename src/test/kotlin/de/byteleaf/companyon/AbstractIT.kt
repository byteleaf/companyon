package de.byteleaf.companyon

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.graphql.spring.boot.test.GraphQLResponse
import com.graphql.spring.boot.test.GraphQLTestSubscription
import com.graphql.spring.boot.test.GraphQLTestTemplate
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.error.ErrorCode
import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.user.control.UserService
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
    protected lateinit var userService: UserService

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var graphQLTestTemplate: GraphQLTestTemplate

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var graphQLTestSubscription: GraphQLTestSubscription

    protected fun performGQLByIdAndInput(gqlOperation: String, id: String, inputPayload: String, skipValidation: Boolean = false): GraphQLResponse =
            performGQL(gqlOperation, "{ \"input\": $inputPayload, \"id\": \"$id\" }", skipValidation)

    protected fun performGQLByInput(gqlOperation: String, inputPayload: String, skipValidation: Boolean = false): GraphQLResponse =
            performGQL(gqlOperation, "{ \"input\": $inputPayload }", skipValidation)

    protected fun performGQLById(gqlOperation: String, id: String, skipValidation: Boolean = false): GraphQLResponse =
            performGQL(gqlOperation, "{ \"id\": \"$id\" }", skipValidation)


    protected fun performGQL(gqlOperation: String, payload: String? = null, skipValidation: Boolean = false): GraphQLResponse {
            val response = graphQLTestTemplate.perform(getGQLResource(gqlOperation), parseJSON(payload))
            return if(skipValidation) response else validateResponse(response)
    }

    protected fun performGQLSubscription(gqlOperation: String, eventFunc: () -> Unit, payload: String? = null, skipValidation: Boolean = false): GraphQLResponse {
        graphQLTestSubscription.reset()
        val firstResponse = graphQLTestSubscription.start(getGQLResource(gqlOperation))
        Executors.newScheduledThreadPool(1).schedule(eventFunc, 100, TimeUnit.MILLISECONDS)
        val secondResponse = firstResponse.awaitAndGetNextResponse(5000, true)
        return if(skipValidation) secondResponse else validateResponse(secondResponse)
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
        projectService.deleteAll()
        companyService.deleteAll()
        userService.deleteAll()
    }


    protected fun getErrorExtensions(response: GraphQLResponse): JsonNode = response.readTree().get("errors").get(0).get("extensions")

    protected fun expectError(response: GraphQLResponse, expectedCode: ErrorCode, expectedType: EntityType, expectedId: String) {
        val errorExtensions = getErrorExtensions(response)
        assertThat(errorExtensions.get("code").asText()).isEqualTo(expectedCode.name)
        assertThat(errorExtensions.get("entityType").asText()).isEqualTo(expectedType.name)
        assertThat(errorExtensions.get("entityId").asText()).isEqualTo(expectedId)
    }
}