#!/bin/bash

set -e

if [ $# -eq 1 ]
then

    ENVIRONMENT=${1}
    echo "ENVIRONMENT :::>>> ${ENVIRONMENT}"

else
    echo "Usage: . ./kube-land-blast.sh <<ENVIRONMENT>>"
    exit 1
fi

pkill -f k8dash | true

pkill -f 8761 | true

pkill -f 8000 | true

echo "CREATING :::>>> Cluster ::: [[[ kube-land ]]]..."

kind create cluster --name kube-land --config ../cluster/kube-land-config.yml

echo "CREATED :::>>> Cluster ::: [[[ kube-land ]]]..."

kind get clusters

./kube-land-k8dash-ui.sh

echo "CREATING :::>>> Namespace ::: [[[ ${ENVIRONMENT} ]]]..."

K8S_MASTER_IP=$(echo $(kubectl get nodes -o jsonpath='{.items[*].status.addresses[?(@.type=="InternalIP")].address}' | awk '{print $1;}'))
echo "Kube Master IP >>> $K8S_MASTER_IP"

cd ../cluster/

#sed -i 's/localhost/${K8S_MASTER_IP}/g' *.yml
find . -type f -name "*.yml" -print0 | xargs -0 sed -i '' -e "s/localhost/${K8S_MASTER_IP}/g"

find . -type f -name "*.yml" -print0 | xargs -0 sed -i '' -e "s/green/${ENVIRONMENT}/g"

mv -i kube-green kube-${ENVIRONMENT}

for f in $(find . -name "*green*"); do mv $f $(echo $f | sed "s/green/${ENVIRONMENT}/"); done

cd ../cluster/kube-${ENVIRONMENT}/

kubectl create -f kube-${ENVIRONMENT}.yml

echo "CREATED :::>>> Namespace ::: [[[ ${ENVIRONMENT} ]]]..."

kubectl get namespaces

echo "CREATING :::>>> Microservices in ::: [[[ ${ENVIRONMENT} ]]]..."

kubectl create -f admin-service.yml

kubectl get services --namespace=kube-${ENVIRONMENT}

cd ../../scripts/

./kube-land-app.sh ${ENVIRONMENT} admin

./kube-land-app.sh ${ENVIRONMENT} accounts

./kube-land-app.sh ${ENVIRONMENT} customers

./kube-land-app.sh ${ENVIRONMENT} users

sleep 40

echo "CREATED :::>>> Microservices in ::: [[[ ${ENVIRONMENT} ]]]..."

echo "FORWARDING :::>>> PORTS in ::: [[[ ${ENVIRONMENT} ]]]..."

kubectl port-forward deployment/admin-deployment 8761:8761 --namespace=kube-${ENVIRONMENT} &
kubectl port-forward deployment/k8dash 8000:4654 --namespace=kube-system &

echo "FORWARDED :::>>> PORTS in ::: [[[ ${ENVIRONMENT} ]]]..."

echo "GENERATING :::>>> Access Key For ::: [[[ K8Dash ]]]..."

./kube-land-ui-access-key.sh

echo "GENERATED :::>>> Access Key For ::: [[[ K8Dash ]]]..."

exit 0
