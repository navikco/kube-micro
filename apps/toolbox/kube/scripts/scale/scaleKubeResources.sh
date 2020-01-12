#!/bin/bash

set -e

if [ $# -ge 3 ]
then

    PROFILE=${1}
    echo "PROFILE :::>>> ${PROFILE}"

    MICROSERVICE=${2}
    echo "MICROSERVICE :::>>> ${MICROSERVICE}"

    REPLICAS=${3}
    echo "REPLICAS :::>>> ${REPLICAS}"

    LIMITS=${4}
    echo "LIMITS :::>>> ${LIMITS}"

    REQUESTS=${5}
    echo "REQUESTS :::>>> ${REQUESTS}"

else
    echo "Usage: . ./scaleKUBEResources.sh <<PROFILE>> <<MICROSERVICE>> <<REPLICAS>>"
    exit 1
fi

# Scale KUBE Kubernetes Resources
# ------------------------------

KUBE_HOME_DIR=/opt/mw/app/kube

echo "KUBE_HOME_DIR ::: ${KUBE_HOME_DIR}"
ls -R ${KUBE_HOME_DIR}/

cd ${KUBE_HOME_DIR}/

kubectl get rs --namespace=kube-${PROFILE}

if [ -z "${REPLICAS}" ]
then

    echo "SCALE >>> [[[SKIP]]] Replicas..."
else
    kubectl scale deployment.v1.apps/${MICROSERVICE}-deployment --replicas=${REPLICAS} --namespace=kube-${PROFILE}
    echo "SCALE >>> [[[DONE]]] Replicas ::: kube-${PROFILE} | ${MICROSERVICE}-deployment | ${REPLICAS}"
fi

if [ -z "${LIMITS}" ]
then

    echo "RESOURCES >>> [[[SKIP]]] Limits..."
else
    kubectl set resources deployment.v1.apps/${MICROSERVICE}-deployment --limits=${LIMITS} --namespace=kube-${PROFILE}
    echo "RESOURCES >>> [[[DONE]]] Limits ::: kube-${PROFILE} | ${MICROSERVICE}-deployment | ${LIMITS}"
fi

if [ -z "${REQUESTS}" ]
then

    echo "RESOURCES >>> [[[SKIP]]] Requests..."
else
    kubectl set resources deployment.v1.apps/${MICROSERVICE}-deployment --requests=${REQUESTS} --namespace=kube-${PROFILE}
    echo "RESOURCES >>> [[[DONE]]] Requests ::: kube-${PROFILE} | ${MICROSERVICE}-deployment | ${REQUESTS}"
fi

kubectl get rs --namespace=kube-${PROFILE}

kubectl get deployments --namespace=kube-${PROFILE}

kubectl describe deployment ${MICROSERVICE}-deployment --namespace=kube-${PROFILE}

exit 0
