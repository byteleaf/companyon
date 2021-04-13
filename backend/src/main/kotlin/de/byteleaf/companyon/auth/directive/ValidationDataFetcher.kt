package de.byteleaf.companyon.auth.directive

import de.byteleaf.companyon.common.error.exception.FatalException
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment

class ValidationDataFetcher(val defaultDataFetcher: DataFetcher<*>) : DataFetcher<Any> {


    override fun get(environment: DataFetchingEnvironment): Any {
        throw FatalException("asdasd")
        return defaultDataFetcher.get(environment)
    }


}