#!/bin/bash

mvn clean package
docker build -t webweb:latest .
docker run -it -p 8080:8080 webweb:latest
