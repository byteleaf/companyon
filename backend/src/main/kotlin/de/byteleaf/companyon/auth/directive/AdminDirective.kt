package de.byteleaf.companyon.auth.directive

import graphql.schema.DataFetcherFactories.wrapDataFetcher
import graphql.schema.DataFetchingEnvironment
import graphql.schema.GraphQLArgument
import graphql.schema.idl.SchemaDirectiveWiring
import graphql.schema.idl.SchemaDirectiveWiringEnvironment
import java.util.function.BiFunction


class AdminDirective : SchemaDirectiveWiring {

    override fun onArgument(environment: SchemaDirectiveWiringEnvironment<GraphQLArgument>): GraphQLArgument? {
        val field = environment.element
        val b = BiFunction { dataFetchingEnvironment: DataFetchingEnvironment, param: Any ->
            45 as Any
        }

        //   throw FatalException("asdsd")
        val dataFetcher = wrapDataFetcher(environment.fieldDataFetcher, b)
        val currentDF = environment.codeRegistry.getDataFetcher(environment.fieldsContainer, environment.fieldDefinition)

//        return field.transform({ builder -> builder.dataFetcher(dataFetcher) });
        environment.codeRegistry.dataFetcher(environment.getFieldsContainer(), environment.getFieldDefinition(), ValidationDataFetcher(currentDF));
        //field.transform { builder: GraphQLArgument.Builder -> builder. }
        return environment.getElement()
    }

//    override fun onArgument(environment: SchemaDirectiveWiringEnvironment<GraphQLArgument>): GraphQLArgument? {
//        val field = environment.element
//        val b = BiFunction { dataFetchingEnvironment: DataFetchingEnvironment, param: Any ->
//            45 as Any
//        }
//
//        //   throw FatalException("asdsd")
//        val dataFetcher = wrapDataFetcher(environment.fieldDataFetcher, b)
////        return field.transform({ builder -> builder.dataFetcher(dataFetcher) });
//        val a = environment.codeRegistry
//        environment.codeRegistry.dataFetcher(environment.getFieldsContainer(), environment.getFieldDefinition(), dataFetcher);
//        //field.transform { builder: GraphQLArgument.Builder -> builder. }
//        return environment.getElement()
//    }

}
