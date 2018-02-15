#!/bin/bash

if [ "$1" = "Database" ]; then
    cd Database
elif [ "$1" = "Facade" ]; then
     cd Facade
elif [ "$1" = "Observeur" ]; then
    cd Observeur
elif [ "$1" = "simulateur" ]; then
    cd simulateur
elif [ "$1" = "webconfig" ]; then
    cd webconfig
else
    echo "Illegal number of parameters
usage: ./build.sh [module_name]
"
    exit -1
fi

echo "Sarting build of $1"

./build.sh
cd ..