package de.byteleaf.companyon.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.graphql.spring.boot.test.GraphQLResponse
import com.graphql.spring.boot.test.GraphQLTestSubscription
import com.graphql.spring.boot.test.GraphQLTestTemplate
import de.byteleaf.companyon.test.util.GQLErrorUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

// The order is important -> test is overwriting some properties
@ActiveProfiles(profiles = ["non-sec", "test"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
// needed, otherwise embedded mongo db will produce a "Could not start process: <EOF>" after executing multiple tests in a row
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
abstract class AbstractIT(val gqlFolder: String) {

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var graphQLTestTemplate: GraphQLTestTemplate

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var graphQLTestSubscription: GraphQLTestSubscription

    protected fun performGQLByIdAndInput(
        gqlOperation: String,
        id: String,
        input: Map<String, Any>,
        skipValidation: Boolean = false
    ): GraphQLResponse =
        performGQL(
            gqlOperation,
            "{ \"input\": ${objectMapper.writeValueAsString(input)}, \"id\": \"$id\" }",
            skipValidation
        )

    protected fun performGQLByInput(
        gqlOperation: String,
        input: Map<String, Any>,
        skipValidation: Boolean = false
    ): GraphQLResponse =
        performGQL(gqlOperation, "{ \"input\":  ${objectMapper.writeValueAsString(input)} }", skipValidation)

    protected fun performGQLById(gqlOperation: String, id: String, skipValidation: Boolean = false): GraphQLResponse =
        performGQL(gqlOperation, "{ \"id\": \"$id\" }", skipValidation)


    protected fun performGQL(
        gqlOperation: String,
        variables: Map<String, Any>,
        skipValidation: Boolean = false
    ): GraphQLResponse = performGQL(gqlOperation, objectMapper.writeValueAsString(variables), skipValidation)


    protected fun performGQL(
        gqlOperation: String,
        payload: String? = null,
        skipValidation: Boolean = false
    ): GraphQLResponse {
        val response = graphQLTestTemplate.perform(getGQLResource(gqlOperation), parseJSON(payload))
        return if (skipValidation) response else GQLErrorUtil.validateResponse(response)
    }

    protected fun performGQLSubscription(
        gqlOperation: String,
        eventFunc: () -> Unit,
        payload: String? = null,
        skipValidation: Boolean = false
    ): GraphQLResponse {
        graphQLTestSubscription.reset()
        val firstResponse = graphQLTestSubscription.start(getGQLResource(gqlOperation))
        Executors.newScheduledThreadPool(1).schedule(eventFunc, 100, TimeUnit.MILLISECONDS)
        val secondResponse = firstResponse.awaitAndGetNextResponse(5000, true)
        return if (skipValidation) secondResponse else GQLErrorUtil.validateResponse(secondResponse)
    }

    /**
     * If the [gqlOperation] contains no slash the default folder will be appended
     */
    protected fun getGQLResource(gqlOperation: String): String {
        if (!gqlOperation.contains("/")) {
            return "graphql/$gqlFolder/$gqlOperation.graphql"
        }
        return "graphql/$gqlOperation.graphql"
    }

    protected fun parseJSON(payload: String? = null): ObjectNode? {
        if (payload != null) return objectMapper.readTree(payload) as ObjectNode?
        return null
    }
}