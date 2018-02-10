#!/bin/bash

mvn clean install

echo "Start webconfig..."
cd webconfig
xterm -e mvn spring-boot:run &

sleep 5

echo "Start Facade..."
cd ../Facade
xterm -e mvn spring-boot:run &

sleep 5

echo "Start Observeur..."
cd ../Observeur
xterm -e mvn spring-boot:run &

sleep 5

echo "Start simulateur"
cd ../simulateur
xterm -e mvn spring-boot:run &