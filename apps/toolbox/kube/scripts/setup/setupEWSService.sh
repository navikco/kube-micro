#!/bin/bash

set -e

if [ $# -eq 5 ]
then

    PROFILE=${1}
    echo "PROFILE :::>>> ${PROFILE}"

    MICROSERVICE=${2}
    echo "MICROSERVICE :::>>> ${MICROSERVICE}"

    PORT=${3}
    echo "PORT :::>>> ${PORT}"

    NODE_PORT=${4}
    echo "NODE_PORT :::>>> ${NODE_PORT}"

    TARGET_PORT=${5}
    echo "TARGET_PORT :::>>> ${TARGET_PORT}"

else
    echo "Usage: . ./setupKUBEService.sh <<PROFILE>> <<MICROSERVICE>> <<PORT>> <<NODE_PORT>> <<TARGET_PORT>>"
    exit 1
fi

# Create KUBE Kubernetes Service
# -----------------------------

KUBE_HOME_DIR=/opt/mw/app/kube

echo "KUBE_HOME_DIR ::: ${KUBE_HOME_DIR}"
ls -R ${KUBE_HOME_DIR}/

cd ${KUBE_HOME_DIR}/

mkdir -p ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/

MICROSERVICE=${MICROSERVICE} PROFILE=${PROFILE} PORT=${PORT} NODE_PORT=${NODE_PORT} TARGET_PORT=${TARGET_PORT} install/scripts/kube-templater.sh install/templates/kube-service.yml > ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/${MICROSERVICE}-service.yml

cat ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/${MICROSERVICE}-service.yml

kubectl create -f ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/${MICROSERVICE}-service.yml --namespace=kube-${PROFILE}

kubectl get services --namespace=kube-${PROFILE}

kubectl describe service ${MICROSERVICE} --namespace=kube-${PROFILE}

exit 0
