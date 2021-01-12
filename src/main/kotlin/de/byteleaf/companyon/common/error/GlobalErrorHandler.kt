package de.byteleaf.companyon.common.error

import de.byteleaf.companyon.common.error.exception.AbstractException
import de.byteleaf.companyon.common.error.exception.EntityNotFoundException
import de.byteleaf.companyon.common.error.exception.FatalException
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.kickstart.spring.error.ErrorContext
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler

@Component
class GlobalErrorHandler {

    @ExceptionHandler(value = [AbstractException::class, FatalException::class])
    fun entityNotFound(ex: AbstractException, errorContext: ErrorContext): List<GraphQLError> {
        return listOf(ex.getGraphQLError(errorContext))
    }
}