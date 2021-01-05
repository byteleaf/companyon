package de.byteleaf.companyon.common.error.exception

import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.error.ErrorCode
import graphql.GraphQLError
import graphql.kickstart.spring.error.ErrorContext

class EntityNotFoundException(val id: String, val entityType: EntityType) : AbstractException(ErrorCode.ENTITY_NOT_FOUND,
        "Entity with id $id and type ${entityType.name} could not be found.") {

    override fun getGraphQLError(errorContext: ErrorContext): GraphQLError = getGraphQLBaseError(errorContext, mutableMapOf(
            Pair("entityId", id), Pair("entityType", entityType.name)
    ))
}