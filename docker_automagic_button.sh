#!/bin/bash

cd webconfig
./build.sh
cd ..

cd Facade
./build.sh
cd ..

cd simulateur
./build.sh
cd ..

#cd Observeur
#./build.sh
#cd ..


echo "C'est parti pour le docker-compose"

#docker-compose up -d

xterm -e 'docker run -it -p 2225:8080 trafficflow/webweb:latest' &
xterm -e 'docker run -it -p 2223:8080 trafficflow/facade:latest' &
xterm -e 'docker run -it -p 2226:8080 trafficflow/simulateur:latest' &
#xterm -e 'docker run -it -p 2227:8080 trafficflow/observateur:latest' &