package de.byteleaf.companyon.test

import com.graphql.spring.boot.test.GraphQLResponse
import com.graphql.spring.boot.test.GraphQLTestTemplate
import de.byteleaf.companyon.test.util.GQLErrorUtil
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractQueryMutationIT(gqlFolder: String) : AbstractIT(gqlFolder) {

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var graphQLTestTemplate: GraphQLTestTemplate


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
}