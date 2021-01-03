package de.byteleaf.companyon.common

import org.springframework.security.test.context.support.WithSecurityContext

@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = WithMockCustomerUserSecurityContextFactory::class)
annotation class WithMockCustomUser(
        val oauth2Subject: String = "oauth2Subject",
        val firstName: String = "Jeff",
        val lastName: String = "Bytezos",
        val email: String = "jeff@byteleaf.de"
)