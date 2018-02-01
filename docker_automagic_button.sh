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

cd Observeur
./build.sh
cd ..


echo "C'est parti pour le docker-compose"

#docker-compose up -d

#xterm -e 'docker run -it -p 8080:8080 webweb:latest' &
#xterm -e 'docker run -it -p 8091:8080 facade:latest' &
#xterm -e 'docker run -it -p 8090:8080 simulateur:latest' &
#xterm -e 'docker run -it -p 8092:8080 observateur:latest' &