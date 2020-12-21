package de.byteleaf.companyon.common.boundary

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import de.byteleaf.companyon.common.errors.GraphQLException

abstract class GraphQLQueryResolver : GraphQLQueryResolver {
    inline fun <reified E> throwIfEmpty(entity: E?, params: Map<String, Any>? = emptyMap()): E {
        if (entity == null) {
            throw GraphQLException("${E::class.simpleName} not found",
                    "common.errors.entityNotFound",
                    params)
        }

        return entity
    }
}