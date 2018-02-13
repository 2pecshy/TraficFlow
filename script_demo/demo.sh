#!/bin/bash

echo "DEMO1: démarer deux simulation en meme temps"

i="0"
NB="1"

if [ "$#" -eq 1 ]; then
    NB=$1
elif [ "$#" -ne 0 ]; then
    echo "Illegal number of parameters
usage: ./demo.sh [nb rep]
"
	exit -1
fi

while [ $i -lt $NB ]
do
    i=$[$i+1]
    echo "start "$i"ème simulation"
    RESPONSE=$(curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data "{\"simulationLength\" : 100, \"simulationStart\" : 3, \"HOVLanes\" : \"true\", \"migrationPendulaire\" : \"False\", \"mapLink\" : \"https://www.openstreetmap.org/api/0.6/map?bbox=7.0258%2C43.6111%2C7.0476%2C43.6203\"}"  http://localhost:2225/config)
    echo "responce> " ${RESPONSE}
done
exit 0