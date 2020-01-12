#!/bin/bash

set -e

if [ $# -eq 6 ]
then

    ENVIRONMENT=${1}
    echo "ENVIRONMENT :::>>> ${ENVIRONMENT}"

    MICROSERVICE=${2}
    echo "MICROSERVICE :::>>> ${MICROSERVICE}"

    PORT=${3}
    echo "PORT :::>>> ${PORT}"

    VERSION=${4}
    echo "VERSION :::>>> ${VERSION}"

    DOCKER_REGISTRY_HOST=${5}
    echo "DOCKER_REGISTRY_HOST :::>>> ${DOCKER_REGISTRY_HOST}"

    DOCKER_REGISTRY_PORT=${6}
    echo "DOCKER_REGISTRY_PORT :::>>> ${DOCKER_REGISTRY_PORT}"

else
    echo "Usage: . ./blastKUBE.sh <<ENVIRONMENT>> <<MICROSERVICE>> <<PORT>> <<VERSION>> <<DOCKER_REGISTRY_HOST>> <<DOCKER_REGISTRY_PORT>>"
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

#docker exec kube-docker-registry rm -rf /var/lib/registry/docker/registry/v2/repositories/securus/mw/kube/
#docker exec kube-docker-registry bin/registry garbage-collect --dry-run /etc/docker/registry/config.yml
#docker exec kube-docker-registry bin/registry garbage-collect /etc/docker/registry/config.yml
#
#docker stop kube-docker-registry
#docker start kube-docker-registry

docker ps

docker images

rm -rf ${KUBE_TOOLBOX_HOME}/build/${MICROSERVICE}*.jar

cp ${KUBE_HOME}/${MICROSERVICE}/build/libs/${MICROSERVICE}*.jar ${KUBE_TOOLBOX_HOME}/build/

chmod 700 ${KUBE_TOOLBOX_HOME}/build/*.jar

cd ${KUBE_TOOLBOX_HOME}/build/

echo "BUILDING :::>>> KUBE Docker Image ::: [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."
docker build --build-arg kubeMicroservice=${MICROSERVICE} -t ${DOCKER_REGISTRY_HOST}:${DOCKER_REGISTRY_PORT}/${MICROSERVICE}:${VERSION} .
echo "BUILT :::>>> KUBE Docker Image ::: [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."

docker ps

docker images

echo "PUSHING :::>>> KUBE Docker Image to Docker Registry ::: [[[ " + ${MICROSERVICE} + " ]]]..."
docker push ${DOCKER_REGISTRY_HOST}:${DOCKER_REGISTRY_PORT}/${MICROSERVICE}:${VERSION}
echo "PUSHED :::>>> KUBE Docker Image to Docker Registry ::: [[[ " + ${MICROSERVICE} + " ]]]..."

#Remove LOCAL Docker KUBE Image
echo "DELETING LOCAL :::>>> KUBE Docker Image ::: [[[ " + ${MICROSERVICE} + " ]]]..."
docker rmi -f $(docker images | grep ${MICROSERVICE}) | true
echo "DELETED LOCAL :::>>> KUBE Docker Image ::: [[[ " + ${MICROSERVICE} + " ]]]..."

INSTANCE=$(ipconfig getifaddr en0)

echo "STARTING :::>>> KUBE Docker Container ::: [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]] on [[[ " + ${INSTANCE} + " ]]]..."
docker run -dti \
    -p ${PORT}:${PORT}/tcp \
    --hostname=ld-midsrvcs06.lab.securustech.net \
    --memory="1g" --memory-swap="2g" \
    --name ${MICROSERVICE}-${VERSION} \
	--mount type=bind,source="${KUBE_DOCKER_HOST_HOME}/mount",target=/opt/mw/mount \
    ${DOCKER_REGISTRY_HOST}:${DOCKER_REGISTRY_PORT}/${MICROSERVICE}:${VERSION} ${ENVIRONMENT} ${MICROSERVICE} ${INSTANCE} 8761
echo "STARTED :::>>> KUBE Docker Container ::: [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."

docker ps

rm -rf ${KUBE_TOOLBOX_HOME}/build/${MICROSERVICE}*.jar

###FOR MAC & WINDOWS POWERSHELL###
docker exec -it ${MICROSERVICE}-${VERSION} bash

###FOR WINDOWS GIT BASH###
#winpty docker exec -it wlsadmin bash

Himanshu - 3763 - XS - 357205095529495
Prarthana - 7858 - 8 - 354877090170815
Krunal - 8933 - 8P - 359499086347854