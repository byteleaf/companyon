# Error handling

## Example

If something went wrong, you can receive a graphql errors as result of your api calls.
Lets say you fire a GetCompany query with a not existing id as parameter:
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
As response you will receive:
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

The important fields for the client are the fields in the **_extensions_** section. 
Present fields in every error:
+ **code:** every error has a specific code 
+ **message:** a error message

### ENTITY_NOT_FOUND

If an entity was not found.
Additional fields:
+ **entityId:** the id of the entity
+ **entityType:** the type of the entity
    + Company
    + Project
    + User
 