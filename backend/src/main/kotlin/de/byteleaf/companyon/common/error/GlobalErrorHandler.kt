package de.byteleaf.companyon.common.error

import de.byteleaf.companyon.common.error.exception.AbstractException
import graphql.GraphQLError
import graphql.kickstart.spring.error.ErrorContext
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler

@Component
class GlobalErrorHandler {

    @ExceptionHandler(value = [AbstractException::class])
    fun entityNotFound(ex: AbstractException, errorContext: ErrorContext): List<GraphQLError> {
        return listOf(ex.getGraphQLError(errorContext))
    }
}
