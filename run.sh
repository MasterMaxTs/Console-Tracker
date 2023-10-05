#!/bin/bash

mvn liquibase:update -Pdocker

java -jar target/tracker.jar