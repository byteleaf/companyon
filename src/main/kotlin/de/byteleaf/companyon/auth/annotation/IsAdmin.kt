package de.byteleaf.companyon.auth.annotation

import org.springframework.security.access.prepost.PreAuthorize


@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasPermission(T(de.byteleaf.companyon.auth.permission.PermissionType).ADMIN, null)")
annotation class IsAdmin