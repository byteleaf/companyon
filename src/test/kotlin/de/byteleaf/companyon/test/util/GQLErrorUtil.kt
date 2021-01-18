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

        fun expectAccessDenied(response: GraphQLResponse) {
            val errorExtensions = getErrorExtensions(response)
            Assertions.assertThat(errorExtensions.get("code").asText()).isEqualTo(ErrorCode.ACCESS_DENIED_NO_ADMIN.name)
        }


        fun validateResponse(response: GraphQLResponse): GraphQLResponse {
            Assertions.assertThat(response.isOk).isTrue()
            Assertions.assertThat(response.readTree().hasNonNull("errors"))
                .describedAs("response has errors")
                .isFalse()
            return response
        }
    }
}