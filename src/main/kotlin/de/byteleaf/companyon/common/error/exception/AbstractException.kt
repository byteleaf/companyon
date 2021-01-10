package de.byteleaf.companyon.common.error.exception

import de.byteleaf.companyon.common.error.ErrorCode
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.kickstart.spring.error.ErrorContext

abstract class AbstractException(val errorCode: ErrorCode, message: String) : RuntimeException(
        "Error code: $errorCode. $message"
) {

    protected fun getGraphQLBaseError(errorContext: ErrorContext, extensions: MutableMap<String, Any> = mutableMapOf<String, Any>()): GraphQLError {
        extensions["code"] = errorCode.name
        extensions["message"] = message!!
        return GraphqlErrorBuilder.newError()
                .message(message)
                .extensions(extensions)
                .errorType(errorContext.errorType)
                .locations(errorContext.locations)
                .path(errorContext.path)
                .build()
    }

    abstract fun getGraphQLError(errorContext: ErrorContext): GraphQLError
}