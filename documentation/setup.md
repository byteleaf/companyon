# Setup

## Configuration for development 

Login into [auth0](https://auth0.com/) and create a new (SPA) Application or select an existing one. 

Update `application.properties`: Copy the domain and enter it as `rest.security.issuer-uri` (e.g. `rest.security.issuer-uri=${OAUTH_URL:https://mytest.eu.auth0.com}`)

As an alternative, you can change the environment variable `OAUTH_URL` when starting the backend. 


## Useful plugins (Intellij)

- [JS GraphQL](https://plugins.jetbrains.com/plugin/8097-js-graphql): Editor for the .graphqls files
- [Maven Helper](https://plugins.jetbrains.com/plugin/7179-maven-helper)


## Database
Create and start a local docker container with a mongo db:

``docker-compose -f docker/docker-compose.yml up``

### Mongo-Express

[Open mongo express](http://localhost:8099)

## GraphQL Playground

[Open graphql playground](http://localhost:8080/playground)]