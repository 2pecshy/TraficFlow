#!/bin/bash

echo "Build du projet"
echo "Création des conteneurs"

./docker_automagic_button.sh

deployFolder="./AutoDeploy"

echo "Création des SNAPSHOT des conteneurs"

docker save --output $deployFolder/facade.tar trafficflow/facade:latest
docker save --output $deployFolder/observateur.tar trafficflow/observateur:latest
docker save --output $deployFolder/simulateur.tar trafficflow/simulateur:latest
docker save --output $deployFolder/webconfig.tar trafficflow/webweb:latest

echo "Copie des boutons de lancement"

cp docker-compose.yml $deployFolder
cp deploy_button.sh $deployFolder
cp touskill.sh $deployFolder
