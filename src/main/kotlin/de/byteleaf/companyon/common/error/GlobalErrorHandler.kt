package de.byteleaf.companyon.common.error

import de.byteleaf.companyon.common.error.exception.AbstractException
import de.byteleaf.companyon.common.error.exception.FatalException
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.kickstart.spring.error.ErrorContext
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.security.access.AccessDeniedException

@Component
class GlobalErrorHandler {

    @ExceptionHandler(value = [AbstractException::class, FatalException::class])
    fun entityNotFound(ex: AbstractException, errorContext: ErrorContext): List<GraphQLError> {
        return listOf(ex.getGraphQLError(errorContext))
    }

    @ExceptionHandler(value = [AccessDeniedException::class])
    fun accessDenied(ex: AccessDeniedException, errorContext: ErrorContext): List<GraphQLError> {
        val extensions = mutableMapOf<String, Any>(Pair("code", ErrorCode.ACCESS_DENIED_NO_ADMIN.name),
        Pair("message", ex.message!!));
        return listOf(GraphqlErrorBuilder.newError()
            .message(ex.message)
            .extensions(extensions)
            .errorType(errorContext.errorType)
            .locations(errorContext.locations)
            .path(errorContext.path)
            .build())
    }
}