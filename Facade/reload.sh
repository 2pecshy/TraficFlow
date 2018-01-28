#!/bin/bash

mvn clean package
docker build -t facade:latest .
