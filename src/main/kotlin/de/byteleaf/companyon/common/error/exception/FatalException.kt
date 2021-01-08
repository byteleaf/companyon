package de.byteleaf.companyon.common.error.exception

import de.byteleaf.companyon.common.error.ErrorCode
import graphql.GraphQLError
import graphql.kickstart.spring.error.ErrorContext

class FatalException(override val message: String) : AbstractException(ErrorCode.FATAL, message) {
    override fun getGraphQLError(errorContext: ErrorContext): GraphQLError = getGraphQLBaseError(errorContext)
}