## TODO

+ Unsubscribe Flowable?

+ .isFieldMatchingEnabled = true // change to false  companyId will be mapped to field id in the entity -> not so nice ;)

timeLogsByUser(id: ID!)
timeLogsByProject(id: ID!)


+ Bean validation??? value object pattern! -> code in InputDtos?

+ Validation event for companies etc -> page with errors? -> fire validation event isEntityExisting

+   // @PreAuthorized(hasProject('DELETE', 'input.projectId'))

+ PermissionExceptions or change the code for all to PERMISSION_DENIED

  "extensions" : {
  "code" : "ACCESS_DENIED_NO_ADMIN",
  "message" : "Zugriff verweigert",
  "parameter": []Any,
  "classification" : "DataFetchingException"
  } -> error message api!!