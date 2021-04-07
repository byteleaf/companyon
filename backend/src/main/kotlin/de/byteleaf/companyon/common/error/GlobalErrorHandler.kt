package de.byteleaf.companyon.common.error

import de.byteleaf.companyon.common.error.exception.AbstractException
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.kickstart.spring.error.ErrorContext
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.validation.ValidationException

@Component
class GlobalErrorHandler {

    @ExceptionHandler(value = [AbstractException::class])
    fun entityNotFound(ex: AbstractException, errorContext: ErrorContext): List<GraphQLError> {
        return listOf(ex.getGraphQLError(errorContext))
    }

    @ExceptionHandler(value = [ValidationException::class])
    fun validation(ex: ValidationException, errorContext: ErrorContext): List<GraphQLError> {
        val extensions = mutableMapOf(Pair(ErrorExtensionKey.CODE, ErrorCode.INPUT_VALIDATION), Pair(ErrorExtensionKey.MESSAGE, ex.message))
        return listOf(
            GraphqlErrorBuilder.newError()
                .message(ex.message)
                .extensions(extensions.mapKeys { it.key.value })
                .errorType(errorContext.errorType)
                .locations(errorContext.locations)
                .path(errorContext.path)
                .build()
        )
    }
}
