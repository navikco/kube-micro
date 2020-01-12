#!/bin/bash

set -e

echo "CURRENT DIRECTORY :::>>> ${PWD}"
#ls -al ${PWD}

echo "Docker KUBE Container Args :::>>> $@"

echo "Which Java???"
which java

#ls -al /usr/bin/java
#ls -al /usr/lib/jvm/

echo "Java Version???"
java -version

if [ $# -eq 4 ]
then

    ENVIRONMENT=${1}
    echo "ENVIRONMENT :::>>> ${ENVIRONMENT}"

    MICROSERVICE=${2}
    echo "MICROSERVICE :::>>> ${MICROSERVICE}"

    KUBE_ADMIN_HOST=$3
    echo ${KUBE_ADMIN_HOST}

    KUBE_ADMIN_PORT=$4
    echo ${KUBE_ADMIN_PORT}

else
    echo "Usage: . ./startKUBE.sh <<ENVIRONMENT>> <<MICROSERVICE>> <<KUBE_ADMIN_HOST>> <<KUBE_ADMIN_PORT>>"
    exit 1
fi

#./setupKUBEDataSources.sh ${ENVIRONMENT}

INSTANCE=$HOSTNAME

KUBE_HOME=/opt/mw/app/kube/

cd ${KUBE_HOME}

echo "$MICROSERVICE :::>>> FileSystem Validation!!!"
ls -R /opt/mw/

ls -al /opt/mw/

#kubeBase=${MICROSERVICE:0:3}
#echo "$MICROSERVICE :::>>> Evaluated kubeBase <<<${kubeBase}>>>"

#mkdir -p /opt/mw/mount/logs/${kubeBase}/${MICROSERVICE}/

mkdir -p ${KUBE_HOME}/kube-core/config/${ENVIRONMENT}/

cp logback-spring.xml ${KUBE_HOME}/kube-core/config/${ENVIRONMENT}/logback-spring.xml

echo "STARTING :::>>> KUBE Microservice [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]] on [[[ " + ${INSTANCE} + " ]]]..."

if [ "$MICROSERVICE" != "admin" ]; then

    java -Dserver.port=8080 -DKUBE_ADMIN_HOST=${KUBE_ADMIN_HOST} -DKUBE_ADMIN_PORT=${KUBE_ADMIN_PORT} -DKUBE_PROFILE=${ENVIRONMENT} -Dspring.profiles.active=${ENVIRONMENT} -jar ${MICROSERVICE}/${MICROSERVICE}-*.jar
else

    java -DKUBE_ADMIN_HOST=${KUBE_ADMIN_HOST} -DKUBE_ADMIN_PORT=${KUBE_ADMIN_PORT} -DKUBE_PROFILE=${ENVIRONMENT} -Dspring.profiles.active=${ENVIRONMENT} -jar ${MICROSERVICE}/${MICROSERVICE}-*.jar

fi

sleep 10

NEWPID=`ps -ef | grep java | grep ${MICROSERVICE} | awk '{print $2}'`

if [ -z "${NEWPID}" ];
then
    echo "*** New PID for KUBE Microservice not detected...  Exiting!!! See logs for more details ***"
    exit 1
fi

echo "STARTED :::>>> KUBE Microservice [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]::: PID :::>>> ${NEWPID}"

exit 0