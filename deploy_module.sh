#!/bin/bash


if [ "$1" = "help" ]; then
    echo "Database -> port : 2229 "
    echo "Facade -> port : 2223 "
    echo "Observeur -> port : 2227 "
    echo "simulateur -> port : 2226 "
    echo "webconfig -> port : 2225 "
    exit 0
fi

echo "$2 dollar deux"

if [ "$#" -ge 2 ]; then
    port=$2
elif [ "$#" -lt 2 ]; then
    echo "Don't forget port"
    exit -1
fi

export port

if [ "$1" = "Database" ]; then
    xterm -e 'docker run -t -p $port:8080 trafficflow/database:latest' &
elif [ "$1" = "Facade" ]; then
    xterm -e 'docker run -t -p $port:8080 trafficflow/facade:latest' &
elif [ "$1" = "Observeur" ]; then
    xterm -e 'docker run -t -p $port:8080 trafficflow/observateur:latest' &
elif [ "$1" = "simulateur" ]; then
    xterm -e 'docker run -t -p $port:8080 trafficflow/simulateur:latest' &
elif [ "$1" = "webconfig" ]; then
    echo "BRONZE -> $port"
    xterm -e 'docker run -t -p $port:8080 trafficflow/webweb:latest' &
else
    echo "Illegal number of parameters
usage: ./deploy_module.sh [module_name]
"
    exit -1
fi

echo "Sarting service $1 on port $port"


