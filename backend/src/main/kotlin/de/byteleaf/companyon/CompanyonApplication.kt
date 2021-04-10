package de.byteleaf.companyon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class CompanyonApplication() {
    init {
        // hibernate/javax.validation messages will be translated into the here defined language
        Locale.setDefault(Locale.US)
    }
}

fun main(args: Array<String>) {
    runApplication<CompanyonApplication>(*args)
}
