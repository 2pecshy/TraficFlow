#!/bin/bash

cd webconfig
bash reload.sh
cd ..

cd Facade
sh reload.sh
cd ..

cd simulateur
sh reload.sh
cd ..

cd Observeur
sh reload.sh
cd ..

echo "C'est parti pour le docker-compose"
docker-compose up -d
#docker run -it -p 8080:8080 webweb:latest
#docker run -it -p 8091:8080 facade:latest
#docker run -it -p 8090:8080 simulateur:latest
#docker run -it -p 8092:8080 observateur:latest