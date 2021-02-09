package de.byteleaf.companyon.test.util

import com.fasterxml.jackson.databind.JsonNode
import com.graphql.spring.boot.test.GraphQLResponse
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.error.ErrorCode
import org.assertj.core.api.Assertions

class GQLErrorUtil {
    companion object {
        private fun getErrorExtensions(response: GraphQLResponse): JsonNode =
            response.readTree().get("errors").get(0).get("extensions")

        fun expectError(
            response: GraphQLResponse,
            expectedCode: ErrorCode,
            expectedType: EntityType,
            expectedId: String
        ) {
            val errorExtensions = getErrorExtensions(response)
            Assertions.assertThat(errorExtensions.get("code").asText()).isEqualTo(expectedCode.name)
            Assertions.assertThat(errorExtensions.get("entityType").asText()).isEqualTo(expectedType.name)
            Assertions.assertThat(errorExtensions.get("entityId").asText()).isEqualTo(expectedId)
        }

        fun expectError(response: GraphQLResponse, expectedErrorCode: ErrorCode) {
            val errorExtensions = getErrorExtensions(response)
            Assertions.assertThat(errorExtensions.get("code").asText()).isEqualTo(expectedErrorCode.name)
        }


        fun validateResponse(response: GraphQLResponse): GraphQLResponse {
            if(!response.isOk) {
                throw AssertionError("HTTP error response code: ${response.statusCode}")
            }

            val json = response.readTree()
            if(json.hasNonNull("errors")) {
                throw AssertionError("GraphQL Errors \r\n" + json.toPrettyString())
            }
            return response
        }
    }
}