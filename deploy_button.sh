#!/bin/bash

echo "Charging containers..."

for fichier in *.tar ; do
        docker load < $fichier
done

echo "Deploying containers..."

docker-compose up -d
