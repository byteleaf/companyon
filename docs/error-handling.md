# Error handling

## Example

If something went wrong, you can receive a graphql errors as result of your api calls.
Let's say you fire a GetCompany query with a not existing id as parameter:
```
query GetCompany($id: ID!) {
    company(id: $id) {
        id
    }
}

variables: {
    id: "Not existing"
}
```
As response, you will receive:
```
{
  "errors": [
    {
      "message": "Error code: ENTITY_NOT_FOUND. Entity with id 5fea0e5749378d5689e915b6 and type COMPANY could not be found.",
      "locations": [
        {
          "line": 2,
          "column": 5
        }
      ],
      "path": [
        "company"
      ],
      "extensions": {
        "entityId": "5fea0e5749378d5689e915b6",
        "entityType": "COMPANY",
        "code": "ENTITY_NOT_FOUND",
        "message": "Error code: ENTITY_NOT_FOUND. Entity with id 5fea0e5749378d5689e915b6 and type COMPANY could not be found.",
        "classification": "DataFetchingException"
      }
    }
  ],
  "data": {
    "company": null
  }
}
```

## Error Types
The important fields you can find in the **_extensions_** section. [Here](../src/main/kotlin/de/byteleaf/companyon/common/error/ErrorExtensionKey.kt) you can find all possible error extension field keys.

**_Required fields in for every error:_**
+ **code:** every error has a specific code. [Here](../src/main/kotlin/de/byteleaf/companyon/auth/permission/PermissionType.kt) you can find all possible error codes.
+ **message:** a error message

### ENTITY_NOT_FOUND
**_Reason:_**

An entity was not found

**_Additional fields:_**:
+ **entityId:** the id of the entity
+ **entityType:** the type of the entity like COMPANY, PROJECT, etc. [Here](../src/main/kotlin/de/byteleaf/companyon/common/entity/EntityType.kt) you can find all entities.

### FATAL
**_Reason:_**

Application configuration is invalid or an internal programming error. Should never happen!

### NO_PERMISSION
**_Reason:_**

User don't has the permission for a resource or action

**_Additional fields:_**:
+ **permissionType:** the type of the missing permission. [Here](../src/main/kotlin/de/byteleaf/companyon/auth/permission/PermissionType.kt) you can find all available permissions.
+ **currentUserId:** the id of the current user, who usually the user with the missing permission
+ **targetUserId:** If the current user tired to read or modify the data of this target user and is not allowed to.
  This field is only present for these permissions: CURRENT_USER_OR_ADMIN
