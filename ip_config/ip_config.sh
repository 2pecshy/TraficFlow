#!/bin/bash

echo "============IP config============"

if [ "$#" -ne 2 ]; then
    echo "Illegal number of parameters
usage: ./ip_config.sh [interface] [username]
"
	exit -1
fi

PATH_TEMPLATE_FILE=template
PATH_FILE_GEN=gen
USER_GROUP_ID=$2
INTERFACE=$1
ADDR_IP=$(ifconfig ${INTERFACE} | grep "inet adr" | cut -d ':' -f 2 | cut -d ' ' -f 1)
echo "
CONFIGURE WITH USER_NAME======> ${USER_GROUP_ID}
CONFIGURE WITH INTERFACE======> ${INTERFACE}
IP FOUND======>" ${ADDR_IP} "
"
IP_KEY=IP_KEY
USER_KEY=USER_KEY

rm ./${PATH_FILE_GEN}/*
cp ${PATH_TEMPLATE_FILE}/*.yml ./${PATH_FILE_GEN}/

cd ${PATH_FILE_GEN}

sed -i "s/${IP_KEY}/${ADDR_IP}/g" *
sed -i "s/${USER_KEY}/${USER_GROUP_ID}/g" *

echo "remove old generated application.yml"
rm ../../simulateur/src/main/resources/application.yml
rm ../../Facade/src/main/resources/application.yml
rm ../../webconfig/src/main/resources/application.yml
rm ../../Observeur/src/main/resources/application.yml


echo "generate ./simulateur/src/main/resources/application.yml"
cp  ./simulateur_application.yml ../../simulateur/src/main/resources
cd ../../simulateur/src/main/resources
mv ./simulateur_application.yml ./application.yml
cd ../../../../ip_config/${PATH_FILE_GEN}

echo "generate ./Facade/src/main/resources/application.yml"
cp  ./Facade_application.yml ../../Facade/src/main/resources
cd ../../Facade/src/main/resources
mv ./Facade_application.yml ./application.yml
cd ../../../../ip_config/${PATH_FILE_GEN}

echo "generate ./webconfig/src/main/resources/application.yml"
cp  ./webconfig_application.yml ../../webconfig/src/main/resources
cd ../../webconfig/src/main/resources
mv ./webconfig_application.yml ./application.yml
cd ../../../../ip_config/${PATH_FILE_GEN}

echo "generate ./Observeur/src/main/resources/application.yml"
cp  ./Observeur_application.yml ../../Observeur/src/main/resources
cd ../../Observeur/src/main/resources
mv ./Observeur_application.yml ./application.yml
cd ../../../../ip_config/${PATH_FILE_GEN}

exit 0