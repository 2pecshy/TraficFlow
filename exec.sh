#!/bin/bash

echo "Start webconfig..."
cd webconfig
mvn spring-boot:run &

sleep 5

echo "Start Facade..."
cd ../Facade
mvn spring-boot:run &

sleep 5

echo "Start Observeur..."
cd ../Observeur
mvn spring-boot:run &

sleep 5

echo "Start simulateur"
cd ../simulateur
mvn spring-boot:run &