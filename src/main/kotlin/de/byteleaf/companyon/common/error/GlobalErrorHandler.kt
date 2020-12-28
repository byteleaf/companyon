package de.byteleaf.companyon.common.error

import de.byteleaf.companyon.common.error.exception.EntityNotFoundException
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.kickstart.spring.error.ErrorContext
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler

@Component
class GlobalErrorHandler {

    @ExceptionHandler(value = [EntityNotFoundException::class])
    fun entityNotFound(ex: EntityNotFoundException, errorContext: ErrorContext): List<GraphQLError> {

        val extensions: MutableMap<String, Any> = mutableMapOf<String, Any>()
        extensions["my-custom-code"] = "some-custom-error"
        val a = GraphqlErrorBuilder.newError()
                .message(ex.message)
                .extensions(extensions)
                .errorType(errorContext.getErrorType())
                .locations(errorContext.getLocations())
                .path(errorContext.getPath())
                .build();
        return listOf(a)
    }
}