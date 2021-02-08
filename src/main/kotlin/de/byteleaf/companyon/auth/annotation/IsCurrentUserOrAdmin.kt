package de.byteleaf.companyon.auth.annotation

import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize

// TODO
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasRole('ROLE_ADMIN')")
annotation class IsCurrentUserOrAdmin