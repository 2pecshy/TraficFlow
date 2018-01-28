#!/bin/bash

mvn clean package
docker build -t webweb:latest .
