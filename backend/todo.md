## TODO

+ Unsubscribe Flowable?

+ .isFieldMatchingEnabled = true // change to false  companyId will be mapped to field id in the entity -> not so nice ;)

timeLogsByUser(id: ID!)
timeLogsByProject(id: ID!)


+ Bean validation??? value object pattern! -> code in InputDtos?

+ Validation event for companies etc -> page with errors? -> fire validation event isEntityExisting

+   // @PreAuthorized(hasProject('DELETE', 'input.projectId'))

+ subscription for timelogs

+ Stack overflow question -> Spring SPEL -> is it possible to inherit #id via annotations? Is it possible to access class variables?
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasPermission('ROLE_ADMIN', #id)")
annotation class IsCurrentUserOrAdmin(val id: String)