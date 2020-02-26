#!/bin/bash

set -e

if [ $# -eq 1 ]
then

    ENVIRONMENT=${1}
    echo "ENVIRONMENT :::>>> ${ENVIRONMENT}"

else
    echo "Usage: . ./kube-land-environment.sh <<ENVIRONMENT>>"
    exit 1
fi

echo "DELETING :::>>> Namespace ::: [[[ " + ${ENVIRONMENT} + " ]]]..."

cd ../cluster/kube-${ENVIRONMENT}/

kubectl get namespaces

kubectl get services --namespace=kube-${ENVIRONMENT}

kubectl delete -f kube-${ENVIRONMENT}.yml | true

sleep 60

pkill -f 8761 | true

echo "DELETED :::>>> Namespace ::: [[[ " + ${ENVIRONMENT} + " ]]]..."

kubectl get namespaces

kubectl get services --namespace=kube-${ENVIRONMENT}

echo "CREATING :::>>> Namespace ::: [[[ " + ${ENVIRONMENT} + " ]]]..."

kubectl create -f kube-${ENVIRONMENT}.yml | true

sleep 20

echo "CREATED :::>>> Namespace ::: [[[ " + ${ENVIRONMENT} + " ]]]..."

kubectl get namespaces

kubectl get services --namespace=kube-${ENVIRONMENT}

echo "STARTING :::>>> Namespace ::: [[[ " + ${ENVIRONMENT} + " ]]]..."

kubectl create -f admin-service.yml

kubectl get services --namespace=kube-${ENVIRONMENT}

kubectl create -f admin/admin-deployment.yml

kubectl get pods --namespace=kube-${ENVIRONMENT}

kubectl get pods --namespace=kube-${ENVIRONMENT}

sleep 40

kubectl port-forward deployment/admin-deployment 8761:8761 --namespace=kube-${ENVIRONMENT} &

echo "STARTED :::>>> Namespace ::: [[[ " + ${ENVIRONMENT} + " ]]]..."

exit 0
