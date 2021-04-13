package de.byteleaf.companyon.auth.directive

import graphql.kickstart.tools.boot.SchemaDirective
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DirectivesConfiguration {

    @Bean
    fun myCustomDirective(): SchemaDirective? {
        return SchemaDirective("admin", AdminDirective())
    }
}