#!/bin/bash

docker exec postgres pg_dump -Fp -o -U keycloak keycloak > init/keycloak-init.sql
