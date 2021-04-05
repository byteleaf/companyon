package de.byteleaf.companyon.common.error.exception

import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.error.ErrorCode
import de.byteleaf.companyon.common.error.ErrorExtensionKey
import graphql.GraphQLError
import graphql.kickstart.spring.error.ErrorContext

class InputValidationException(val entityType: EntityType, message: String) : AbstractException(
    ErrorCode.INPUT_VALIDATION,
    "Invalid input detected for entity type $entityType: $message"
) {

    override fun getGraphQLError(errorContext: ErrorContext): GraphQLError = getGraphQLBaseError(
        errorContext, mutableMapOf(Pair(ErrorExtensionKey.ENTITY_TYPE, entityType.name))
    )
}