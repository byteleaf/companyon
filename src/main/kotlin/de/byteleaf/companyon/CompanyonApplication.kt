package de.byteleaf.companyon

import com.oembedler.moon.graphql.boot.GraphQLWebAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [GraphQLWebAutoConfiguration::class])
class CompanyonApplication

fun main(args: Array<String>) {
    runApplication<CompanyonApplication>(*args)
}