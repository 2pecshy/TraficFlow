#!/bin/bash

mvn clean package
docker build -t trafficflow/webweb:latest .

