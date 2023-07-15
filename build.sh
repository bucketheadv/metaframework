#!/usr/bin/env sh
mvn clean package -Pnative -DskipTests -H:ReflectionConfigurationFiles=src/main/resources/reflection-guice.json
