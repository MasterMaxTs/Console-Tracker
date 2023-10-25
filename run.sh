#!/bin/bash

mvn liquibase:update -Pdocker

java -jar target/console_tracker.jar