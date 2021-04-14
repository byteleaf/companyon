package de.byteleaf.companyon.auth.directive

import graphql.schema.DataFetcherFactories.wrapDataFetcher
import graphql.schema.DataFetchingEnvironment
import graphql.schema.GraphQLArgument
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.idl.SchemaDirectiveWiring
import graphql.schema.idl.SchemaDirectiveWiringEnvironment
import java.util.function.BiFunction


class AdminDirective : SchemaDirectiveWiring {

    override fun onArgument(environment: SchemaDirectiveWiringEnvironment<GraphQLArgument>): GraphQLArgument? {
        val currentDataFetcher = environment.codeRegistry.getDataFetcher(environment.fieldsContainer, environment.fieldDefinition)
        environment.codeRegistry.dataFetcher(environment.fieldsContainer, environment.fieldDefinition, AuthenticationDataFetcher(currentDataFetcher))
        return super.onArgument(environment)
    }


    override fun onField(environment: SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition>): GraphQLFieldDefinition {


        val field = environment.element
        val b = BiFunction { dataFetchingEnvironment: DataFetchingEnvironment, param: Any ->
            

            45 as Any

        }

        val dataFetcher = wrapDataFetcher(environment.fieldDataFetcher, b)
        return field.transform({ builder -> builder.dataFetcher(dataFetcher) });
    }
}
