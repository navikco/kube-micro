#!/bin/bash

echo "Deploying Kube Land Microservice to LOVELY MAC!!!"

KUBE_HOME=$PWD

if [ $# -eq 3 ]
then

    MICROSERVICE=$1
    echo ${MICROSERVICE}

    PORT=$2
    echo ${PORT}

    BUILD=$3
    echo ${BUILD}

        cd ${KUBE_HOME}/${MICROSERVICE}

    if [ "$BUILD" == "build" ]
    then
        ./gradlew clean idea build
    fi

    echo "###Starting Microservice [${MICROSERVICE}] on [${PORT}] ###"

    if [ "$MICROSERVICE" != "admin" ]
    then
        java -Dserver.port=${PORT} -DKUBE_ADMIN_HOST=localhost -DKUBE_ADMIN_PORT=8761 -jar build/libs/${MICROSERVICE}-*.jar
    else
        java -Dserver.port=8761 -DKUBE_ADMIN_HOST=localhost -DKUBE_ADMIN_PORT=8761 -jar build/libs/${MICROSERVICE}-*.jar
    fi

else
    echo "Usage: . ./kube_hack.sh <<MICROSERVICE>> <<PORT>> <<build/nobuild>>"
    exit 1
fi
