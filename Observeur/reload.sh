#!/bin/bash

mvn clean package
docker build -t observateur:latest .
