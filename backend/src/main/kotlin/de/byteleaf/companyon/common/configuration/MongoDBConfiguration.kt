package de.byteleaf.companyon.auth.configuration

import de.byteleaf.companyon.user.dto.output.User
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@Configuration
@EnableMongoAuditing
class MongoDBConfiguration {
    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return CustomAuditorAware()
    }

    private class CustomAuditorAware : AuditorAware<String> {
        override fun getCurrentAuditor(): Optional<String> {
            val authentication: Authentication? = SecurityContextHolder.getContext().authentication
            return if (authentication == null || !authentication.isAuthenticated) {
                Optional.of("UNKNOWN")
            } else {
                Optional.of((authentication.principal as User).id)
            }
        }
    }
}