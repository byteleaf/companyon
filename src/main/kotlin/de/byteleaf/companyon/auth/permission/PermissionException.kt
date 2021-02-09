package de.byteleaf.companyon.auth.permission

import de.byteleaf.companyon.common.error.ErrorCode
import de.byteleaf.companyon.common.error.exception.AbstractException
import graphql.GraphQLError
import graphql.kickstart.spring.error.ErrorContext


class PermissionException(val type: PermissionType, message: String, private val parameters: Map<String, Any>):
    AbstractException(ErrorCode.ACCESS_DENIED, message) {

    override fun getGraphQLError(errorContext: ErrorContext): GraphQLError {
        val gqlParameters = mutableMapOf(Pair("permissionType", type), Pair("message", message!!))
        gqlParameters.putAll(parameters)
        return getGraphQLBaseError(errorContext, gqlParameters)
    }
}