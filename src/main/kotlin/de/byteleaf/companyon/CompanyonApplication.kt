package de.byteleaf.companyon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CompanyonApplication {
    fun main(args: Array<String>) {
        runApplication<CompanyonApplication>(*args)
    }
}
