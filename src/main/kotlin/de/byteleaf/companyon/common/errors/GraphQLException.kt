package de.byteleaf.companyon.common.errors

import graphql.ErrorType
import graphql.GraphQLError
import graphql.language.SourceLocation

class GraphQLException(
        @JvmField override val message: String,
        private val i18nMessage: String,
        private val parameters: Map<String, Any>? = mutableMapOf()
) : RuntimeException(message), GraphQLError {
    override fun getMessage(): String? = super.message

    override fun getLocations(): List<SourceLocation> = listOf()

    override fun getErrorType(): ErrorType = ErrorType.DataFetchingException

    override fun getExtensions(): MutableMap<String, Any> {
        return mutableMapOf(
                "parameters" to (parameters ?: mutableMapOf()),
                "i18n" to i18nMessage
        )
    }
}
