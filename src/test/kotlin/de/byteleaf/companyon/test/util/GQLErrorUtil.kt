package de.byteleaf.companyon.test.util

import com.fasterxml.jackson.databind.JsonNode
import com.graphql.spring.boot.test.GraphQLResponse
import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.auth.permission.PermissionType
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.error.ErrorCode
import de.byteleaf.companyon.common.error.ErrorExtensionKey
import org.assertj.core.api.Assertions

class GQLErrorUtil {
    companion object {
        private fun getErrorExtensions(response: GraphQLResponse): JsonNode {
            val errors = response.readTree().get("errors") ?: throw AssertionError("GQL errors expected, but no errors found!")
            return errors.get(0).get("extensions")
        }

        fun expectError(
            response: GraphQLResponse,
            expectedCode: ErrorCode,
            expectedType: EntityType,
            expectedId: String
        ) {
            val errorExtensions = getErrorExtensions(response)
            Assertions.assertThat(errorExtensions.get(ErrorExtensionKey.CODE.value).asText()).isEqualTo(expectedCode.name)
            Assertions.assertThat(errorExtensions.get(ErrorExtensionKey.ENTITY_TYPE.value).asText()).isEqualTo(expectedType.name)
            Assertions.assertThat(errorExtensions.get(ErrorExtensionKey.ENTITY_ID.value).asText()).isEqualTo(expectedId)
        }

        fun expectError(response: GraphQLResponse, expectedErrorCode: ErrorCode) {
            val errorExtensions = getErrorExtensions(response)
            Assertions.assertThat(errorExtensions.get("code").asText()).isEqualTo(expectedErrorCode.name)
        }

        fun expectNoPermission(response: GraphQLResponse, permissionType: PermissionType, key1: ErrorExtensionKey? = null, value1: String? = null) {
            val errorExtensions = getErrorExtensions(response)
            Assertions.assertThat(errorExtensions.get(ErrorExtensionKey.CODE.value).asText()).isEqualTo(ErrorCode.NO_PERMISSION.name)
            Assertions.assertThat(errorExtensions.get(ErrorExtensionKey.PERMISSION_TYPE.value).asText()).isEqualTo(permissionType.name)
            Assertions.assertThat(errorExtensions.get(ErrorExtensionKey.CURRENT_USER_ID.value).asText()).isEqualTo(NonSecConfiguration.NON_SEC_USER_ID)
            if(key1 != null) Assertions.assertThat(errorExtensions.get(ErrorExtensionKey.TARGET_USER_ID.value).asText()).isEqualTo(value1)
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