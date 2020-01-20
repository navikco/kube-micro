#!/bin/bash

set -e

if [ $# -eq 4 ]
then

    ENVIRONMENT=${1}
    echo "ENVIRONMENT :::>>> ${ENVIRONMENT}"

    MICROSERVICE=${2}
    echo "MICROSERVICE :::>>> ${MICROSERVICE}"

    PORT=${3}
    echo "PORT :::>>> ${PORT}"

    VERSION=${4}
    echo "VERSION :::>>> ${VERSION}"

#    DOCKER_REGISTRY_HOST=${5}
#    echo "DOCKER_REGISTRY_HOST :::>>> ${DOCKER_REGISTRY_HOST}"

#    DOCKER_REGISTRY_PORT=${6}
#    echo "DOCKER_REGISTRY_PORT :::>>> ${DOCKER_REGISTRY_PORT}"

else
    echo "Usage: . ./blastKUBE.sh <<ENVIRONMENT>> <<MICROSERVICE>> <<PORT>> <<VERSION>>"
    exit 1
fi

# KUBE Microservice running as Docker Container
# --------------------------------------------
# https://localhost:20024/kube/customers/info/index.html

# KUBE Microservice Docker Container sudo/root Password
# ----------------
# welcome1

###################
###ONLY SET THIS###
###################
KUBE_TOOLBOX_HOME=${PWD}
KUBE_HOME=${PWD}/../../../
KUBE_DOCKER_HOST_HOME=/opt/mw

docker ps

docker images

#Stop and Remove currently running (If any!) Docker Containers for SCN Weblogic Image
echo "STOPPING :::>>> KUBE Docker Container ::: [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."
docker rm -f $(docker stop $(docker ps -a -q -f name=${MICROSERVICE})) | true
#docker rm -f $(docker stop $(docker ps -a -q -f name=kube-customers)) | true

echo "STOPPED :::>>> KUBE Docker Container ::: [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."

#Remove Docker KUBE Image
echo "STOPPING :::>>> KUBE Docker Image ::: [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."
docker rmi -f $(docker images | grep ${MICROSERVICE}) | true
docker rmi -f $(docker images | grep none) | true
echo "STOPPED :::>>> KUBE Docker Image ::: [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."

docker ps

docker images

rm -rf ${KUBE_TOOLBOX_HOME}/build/${MICROSERVICE}*.jar

cp ${KUBE_HOME}/${MICROSERVICE}/build/libs/${MICROSERVICE}*.jar ${KUBE_TOOLBOX_HOME}/build/

chmod 700 ${KUBE_TOOLBOX_HOME}/build/*.jar

cd ${KUBE_TOOLBOX_HOME}/build/

echo "BUILDING :::>>> KUBE Docker Image ::: [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."
#docker build --build-arg kubeMicroservice=${MICROSERVICE} -t ${DOCKER_REGISTRY_HOST}:${DOCKER_REGISTRY_PORT}/${MICROSERVICE}:${VERSION} .
docker build --build-arg kubeMicroservice=${MICROSERVICE} -t navikco/${MICROSERVICE}:${VERSION} .

#docker rmi navikco/${MICROSERVICE}:latest | true
#docker tag navikco/${MICROSERVICE}:${VERSION} navikco/${MICROSERVICE}:latest
#docker push navikco/${MICROSERVICE}:${VERSION}
#docker push navikco/${MICROSERVICE}:latest

echo "BUILT :::>>> KUBE Docker Image ::: [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."

docker ps

docker images

docker login --username=navikco --password=Frisc0tx!

echo "PUSHING :::>>> KUBE Docker Image to Docker Registry ::: [[[ " + ${MICROSERVICE} + " ]]]..."
#docker push ${DOCKER_REGISTRY_HOST}:${DOCKER_REGISTRY_PORT}/${MICROSERVICE}:${VERSION}
#docker push navikco/kube:${MICROSERVICE}-${VERSION}
echo "PUSHED :::>>> KUBE Docker Image to Docker Registry ::: [[[ " + ${MICROSERVICE} + " ]]]..."

#Remove LOCAL Docker KUBE Image
echo "DELETING LOCAL :::>>> KUBE Docker Image ::: [[[ " + ${MICROSERVICE} + " ]]]..."
#docker rmi -f $(docker images | grep ${MICROSERVICE}) | true
echo "DELETED LOCAL :::>>> KUBE Docker Image ::: [[[ " + ${MICROSERVICE} + " ]]]..."

INSTANCE=$(ipconfig getifaddr en0)

echo "STARTING :::>>> KUBE Docker Container ::: [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]] on [[[ " + ${INSTANCE} + " ]]]..."
docker run -dti \
    -p ${PORT}:${PORT}/tcp \
    --hostname=ld-midsrvcs06.lab.kubeland.net \
    --memory="1g" --memory-swap="2g" \
    --name ${MICROSERVICE}-${VERSION} \
	--mount type=bind,source="${KUBE_DOCKER_HOST_HOME}/mount",target=/opt/mw/mount \
    navikco/${MICROSERVICE}:${VERSION} ${ENVIRONMENT} ${MICROSERVICE} ${INSTANCE} 8761
echo "STARTED :::>>> KUBE Docker Container ::: [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."

docker ps

rm -rf ${KUBE_TOOLBOX_HOME}/build/${MICROSERVICE}*.jar

###FOR MAC & WINDOWS POWERSHELL###
docker exec -it ${MICROSERVICE}-${VERSION} bash

###FOR WINDOWS GIT BASH###
#winpty docker exec -it wlsadmin bash
