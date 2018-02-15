#!/bin/bash

#Lancer avec wlan0 docko pour docker
#Lancer sans rien pour regular launch

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

cd ../



echo "Start build webconfig..."
cd webconfig
./build.sh

sleep 1

echo "Start build Facade..."
cd ../Facade
./build.sh

sleep 1

echo "Start build Observeur..."
cd ../Observeur
./build.sh

sleep 1

echo "Start build simulateur"
cd ../simulateur
./build.sh

sleep 1

#echo "Start build Database"
#cd ../Database
#./build.sh

sleep 1

#echo "integrationTEST"
#cd ../integration-tests
#./build.sh