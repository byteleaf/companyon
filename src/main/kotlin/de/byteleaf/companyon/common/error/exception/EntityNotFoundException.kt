package de.byteleaf.companyon.common.error.exception

import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.error.ErrorCode
import de.byteleaf.companyon.common.error.ErrorExtensionKey
import graphql.GraphQLError
import graphql.kickstart.spring.error.ErrorContext

class EntityNotFoundException(val id: String, val entityType: EntityType) : AbstractException(ErrorCode.ENTITY_NOT_FOUND,
        "Entity with id $id and type ${entityType.name} could not be found.") {

    override fun getGraphQLError(errorContext: ErrorContext): GraphQLError = getGraphQLBaseError(errorContext, mutableMapOf(
            Pair(ErrorExtensionKey.ENTITY_ID, id), Pair(ErrorExtensionKey.ENTITY_TYPE, entityType.name)
    ))
}