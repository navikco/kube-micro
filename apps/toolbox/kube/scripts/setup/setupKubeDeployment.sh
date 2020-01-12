#!/bin/bash

set -e

if [ $# -eq 6 ]
then

    PROFILE=${1}
    echo "PROFILE :::>>> ${PROFILE}"

    MICROSERVICE=${2}
    echo "MICROSERVICE :::>>> ${MICROSERVICE}"

    VERSION=${3}
    echo "VERSION :::>>> ${VERSION}"

    CONTAINER_PORT=${4}
    echo "CONTAINER_PORT :::>>> ${CONTAINER_PORT}"

    ADMIN_HOST=${5}
    echo "ADMIN_HOST :::>>> ${ADMIN_HOST}"

    ADMIN_PORT=${6}
    echo "ADMIN_PORT :::>>> ${ADMIN_PORT}"

else
    echo "Usage: . ./setupKUBEDeployment.sh <<PROFILE>> <<MICROSERVICE>> <<VERSION>> <<CONTAINER_PORT>> <<ADMIN_HOST>> <<ADMIN_PORT>>"
    exit 1
fi

# Create KUBE Kubernetes Deployment
# --------------------------------

KUBE_HOME_DIR=/opt/mw/app/kube

echo "KUBE_HOME_DIR ::: ${KUBE_HOME_DIR}"
ls -R ${KUBE_HOME_DIR}/

cd ${KUBE_HOME_DIR}/

mkdir -p ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/${MICROSERVICE}/

MICROSERVICE=${MICROSERVICE} PROFILE=${PROFILE} VERSION=${VERSION} CONTAINER_PORT=${CONTAINER_PORT} ADMIN_HOST=${ADMIN_HOST} ADMIN_PORT=${ADMIN_PORT} install/scripts/kube-templater.sh install/templates/kube-deployment.yml -f install/properties/kube-deployment.properties > ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/${MICROSERVICE}/${MICROSERVICE}-deployment.yml

cat ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/${MICROSERVICE}/${MICROSERVICE}-deployment.yml

kubectl create -f ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/${MICROSERVICE}/${MICROSERVICE}-deployment.yml --namespace=kube-${PROFILE}

kubectl get deployments --namespace=kube-${PROFILE}

kubectl describe deployment ${MICROSERVICE}-deployment --namespace=kube-${PROFILE}

sleep 15

exit 0
