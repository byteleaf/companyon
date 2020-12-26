# Companyon



## Setup

[Click here to see more details](documentation/setup.md)



## Run without IDE via Maven

```
mvn spring-boot:run
```

Without security
```
mvn spring-boot:run -Dspring-boot.run.profiles=non-sec
```

## TODO

+ Add documentation

+ Login username & passwort

type File {
  id: ID!
  url: string!
  mimetype: string!
}
timeLogsByUser(id: ID!)
timeLogsByCompany(id: ID!)
timeLogsByProject(id: ID!)
