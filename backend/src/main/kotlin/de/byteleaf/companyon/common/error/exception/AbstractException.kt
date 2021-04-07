package de.byteleaf.companyon.common.error.exception

import de.byteleaf.companyon.common.error.ErrorCode
import de.byteleaf.companyon.common.error.ErrorExtensionKey
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.kickstart.spring.error.ErrorContext

abstract class AbstractException(val errorCode: ErrorCode, message: String) : RuntimeException(
    "Error code: $errorCode. $message"
) {

    protected fun getGraphQLBaseError(errorContext: ErrorContext, extensions: MutableMap<ErrorExtensionKey, Any?> = mutableMapOf()): GraphQLError {
        extensions[ErrorExtensionKey.CODE] = errorCode.name
        extensions[ErrorExtensionKey.MESSAGE] = message!!
        val finalExtensions = extensions.filter { it.value != null }.mapKeys { it.key.value }

        return GraphqlErrorBuilder.newError()
            .message(message)
            .extensions(finalExtensions)
            .errorType(errorContext.errorType)
            .locations(errorContext.locations)
            .path(errorContext.path)
            .build()
    }

    abstract fun getGraphQLError(errorContext: ErrorContext): GraphQLError
}
