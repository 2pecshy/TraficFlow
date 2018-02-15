#!/bin/bash

DEFAULT_INTERFACE=lo
DEFAULT_USER=guest

cd ./tools/
if [ "$#" -eq 0 ]; then
    ./ip_config.sh ${DEFAULT_INTERFACE} ${DEFAULT_USER}
elif [ "$#" -eq 2 ]; then
    ./ip_config.sh $1 $2
else
    echo "Illegal number of parameters
usage: ./build.sh option[interface] [username]
"
    exit -1
fi

./ip_config.sh wlan0 docko
cd ../



echo "Start build webconfig..."
cd webconfig
mvn clean install

sleep 15

echo "Start build Facade..."
cd ../Facade
mvn clean install

sleep 15

echo "Start build Observeur..."
cd ../Observeur
mvn clean install

sleep 15

echo "Start build simulateur"
cd ../simulateur
mvn clean install

sleep 15

echo "Start build Database"
cd ../Database
mvn clean install

echo "integrationTEST"
cd ../ integration-tests
mvn clean install