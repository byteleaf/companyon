package de.byteleaf.companyon.auth.directive

import graphql.schema.DataFetchingEnvironment
import graphql.schema.GraphQLArgument
import graphql.schema.idl.SchemaDirectiveWiring
import graphql.schema.idl.SchemaDirectiveWiringEnvironment
import java.util.function.BiFunction


class AdminFieldDirective : SchemaDirectiveWiring {

    override fun onArgument(environment: SchemaDirectiveWiringEnvironment<GraphQLArgument>): GraphQLArgument? {
        val field = environment.element
        val b = BiFunction { dataFetchingEnvironment: DataFetchingEnvironment, param: Any ->
            45 as Any
        }

//        val dataFetcher = wrapDataFetcher(env.fieldDataFetcher, b)
//        return field.transform({ builder -> builder.dataFetcher(dataFetcher) });
        return environment.getElement()
    }

}
