[![Codacy Badge](https://app.codacy.com/project/badge/Grade/d414a11497004ef58e797d483f08b03a)](https://www.codacy.com/gh/byteleaf/companyon-backend/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=byteleaf/companyon-backend&amp;utm_campaign=Badge_Grade)

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

+ createUser needed? 

+ getCurrentUser: What happens if the user data changes? how to keep this call up to date ;)

timeLogsByUser(id: ID!)
timeLogsByCompany(id: ID!)
timeLogsByProject(id: ID!)
