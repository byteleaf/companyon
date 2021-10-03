package de.byteleaf.companyon.common.configuration

import graphql.scalars.ExtendedScalars
import graphql.schema.GraphQLScalarType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class GraphQLExtendedScalarsConfiguration {

    @Bean
    fun scalarDateTime(): GraphQLScalarType? = ExtendedScalars.DateTime

    @Bean
    fun scalarDate(): GraphQLScalarType? = ExtendedScalars.Date
}