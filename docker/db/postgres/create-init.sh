#!/bin/bash

docker exec postgres pg_dump -Fp -U keycloak keycloak > init/keycloak-init.sql
