package de.byteleaf.companyon.security.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfigurer : WebMvcConfigurer {

    @Value("\${app.cors.allowed-origins}")
    private lateinit var corsAllowedOrigins: String

    @Suppress("SpreadOperator") // there is no other way to pass a dynamic array without the spread operator
    override fun addCorsMappings(registry: CorsRegistry) {
        val allowedOrigins = corsAllowedOrigins.split(",").toTypedArray()

        if (allowedOrigins.isEmpty()) {
            return
        }

        registry.addMapping("/**")
                .allowedHeaders("origin", "authorization", "content-type")
                .allowedOrigins(*allowedOrigins)
                .allowedMethods("GET", "POST")

    }
}