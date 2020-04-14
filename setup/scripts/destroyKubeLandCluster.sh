#!/bin/bash

set -e

if [[ $# -eq 1 ]]
then

    ENVIRONMENT=${1}
    echo "ENVIRONMENT :::>>> ${ENVIRONMENT}"

else
    echo "Usage: ./destroyKubeLandCluster.sh <<ENVIRONMENT>>"
    exit 1
fi

# Destroy KAFKA Ecosystem
# ----------------------------

pkill -f 8761 | true

pkill -f 8000 | true

echo "DESTROYING Namespace :::>>> [[[ kube-${ENVIRONMENT} ]]]..."

kubectl delete -f kube-${ENVIRONMENT}.yml | true

echo "DESTROYING :::>>> Cluster ::: [[[ kube-land ]]]..."

kind delete cluster --name kube-land

sleep 20
echo "DESTROYED :::>>> Cluster ::: [[[ kube-land ]]]..."

echo "DESTROYED Namespace :::>>> [[[ kube-${ENVIRONMENT} ]]]..."

exit 0
