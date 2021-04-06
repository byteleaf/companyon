package de.byteleaf.companyon.common.configuration

import graphql.kickstart.tools.boot.SchemaDirective
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class GraphQLDirectiveConfiguration {

    @Bean
    fun myCustomDirective(): SchemaDirective? {
        return SchemaDirective("uppercase", UppercaseDirective())
    }
}