# Companyon



## Setup

[Click here to see more details](documentation/setup.md)

## How to start

**Run without IDE via Maven**
```
mvn spring-boot:run
```

**Without security**
```
mvn spring-boot:run -Dspring-boot.run.profiles=non-sec
```

## Error handling

[Click here to read more about the error handling](documentation/error-handling.md)

## TODO

+ Unsubscribe Flowable?

+ .isFieldMatchingEnabled = true // change to false  companyId will be mapped to field id in the entity -> not so nice ;)

type File {
  id: ID!
  url: string!
  mimetype: string!
}
timeLogsByUser(id: ID!)
timeLogsByCompany(id: ID!)
timeLogsByProject(id: ID!)
