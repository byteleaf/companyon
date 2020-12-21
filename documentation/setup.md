# Setup

## Configuration for development 

Login into [auth0](https://auth0.com/) and create a new (SPA) Application or select an existing one. 

Update `application.properties`: Copy the domain and enter it as `rest.security.issuer-uri` (e.g. `rest.security.issuer-uri=${OAUTH_URL:https://mytest.eu.auth0.com}`)

As an alternative, you can change the environment variable `OAUTH_URL` when starting the backend. 


## Plugins

- JS GraphQL: Editor for the .graphqls files


## Database
Create and start a local docker container with a mongo db:

``docker-compose -f docker/docker-compose.yml up``

### Mongo-Express

[http://localhost:8081](http://localhost:8081)
