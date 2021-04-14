package de.byteleaf.companyon.auth.directive

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment

class AuthenticationDataFetcher(private val defaultDataFetcher: DataFetcher<*>) : DataFetcher<Any> {


    override fun get(environment: DataFetchingEnvironment): Any {
        

        return defaultDataFetcher.get(environment)
    }


}