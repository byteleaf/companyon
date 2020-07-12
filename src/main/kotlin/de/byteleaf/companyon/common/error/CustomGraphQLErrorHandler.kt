package de.byteleaf.companyon.common.error

import graphql.GraphQLError
import graphql.servlet.GenericGraphQLError
import graphql.servlet.GraphQLErrorHandler
import org.springframework.stereotype.Component

@Component
class CustomGraphQLErrorHandler : GraphQLErrorHandler {

    override fun processErrors(param: MutableList<GraphQLError>?): MutableList<GraphQLError> {
        val type = param?.get(0)
        return mutableListOf(GenericGraphQLError(type?.message))
    }

}
