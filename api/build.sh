#!/bin/zsh
# Small build script to build and deploy the api
# This is just a small app so no expectation of versioning etc
mvn clean install
docker build . -t monitoringapi:0.0.1

