package de.byteleaf.companyon.auth.annotation

import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasPermission('ROLE_ADMIN', #id)")
annotation class IsCurrentUserOrAdmin(val id: String)