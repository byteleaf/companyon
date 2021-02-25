package de.byteleaf.companyon.auth.permission

import de.byteleaf.companyon.common.error.ErrorCode
import de.byteleaf.companyon.common.error.ErrorExtensionKey
import de.byteleaf.companyon.common.error.exception.AbstractException
import graphql.GraphQLError
import graphql.kickstart.spring.error.ErrorContext


class PermissionException(val type: PermissionType, message: String, private val parameters: Map<ErrorExtensionKey, Any?>):
    AbstractException(ErrorCode.NO_PERMISSION, message) {

    override fun getGraphQLError(errorContext: ErrorContext): GraphQLError {
        val gqlParameters: MutableMap<ErrorExtensionKey, Any?> = mutableMapOf(Pair(ErrorExtensionKey.PERMISSION_TYPE, type))
        gqlParameters.putAll(parameters)
        return getGraphQLBaseError(errorContext, gqlParameters)
    }
}