package de.byteleaf.companyon.auth.annotation

import org.springframework.security.access.annotation.Secured


@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Secured("ROLE_ADMIN")
annotation class IsAdmin