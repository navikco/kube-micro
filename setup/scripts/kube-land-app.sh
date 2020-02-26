#!/bin/bash

set -e

if [ $# -eq 2 ]
then

    ENVIRONMENT=${1}
    echo "ENVIRONMENT :::>>> ${ENVIRONMENT}"

    MICROSERVICE=${2}
    echo "MICROSERVICE :::>>> ${MICROSERVICE}"
else
    echo "Usage: . ./kube-land-app.sh <<ENVIRONMENT>> <<MICROSERVICE>>"
    exit 1
fi

cd ../cluster/kube-${ENVIRONMENT}/

kubectl get namespaces

kubectl get services --namespace=kube-${ENVIRONMENT}

echo "DELETING :::>>> [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."

kubectl delete -f ${MICROSERVICE}/${MICROSERVICE}-deployment.yml | true

sleep 20

echo "DELETED :::>>> [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."

kubectl get pods --namespace=kube-${ENVIRONMENT}

echo "STARTING :::>>> [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."

kubectl create -f ${MICROSERVICE}/${MICROSERVICE}-deployment.yml

sleep 20

kubectl get pods --namespace=kube-${ENVIRONMENT}

echo "STARTED :::>>> [[[ " + ${MICROSERVICE} + " ]]] in [[[ " + ${ENVIRONMENT} + " ]]]..."

exit 0
