#!/bin/bash

echo "Start webconfig..."
cd webconfig
mvn clean package &

sleep 15

echo "Start Facade..."
cd ../Facade
mvn clean package &

sleep 15

echo "Start Observeur..."
cd ../Observeur
mvn clean package &

sleep 15

echo "Start simulateur"
cd ../simulateur
mvn clean package &