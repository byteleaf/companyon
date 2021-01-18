## TODO

+ Unsubscribe Flowable?

+ .isFieldMatchingEnabled = true // change to false  companyId will be mapped to field id in the entity -> not so nice ;)

+ createUser needed?

+ getCurrentUser: What happens if the user data changes? how to keep this call up to date ;)

timeLogsByUser(id: ID!)
timeLogsByCompany(id: ID!)
timeLogsByProject(id: ID!)


+ Bean validation??? value object pattern! -> code in InputDtos?

+ Validation event for companies etc -> page with errors?

+   // @PreAuthorized(hasProject('DELETE', 'input.projectId'))