#!/bin/bash

set -e

if [ $# -eq 1 ]
then

    ENVIRONMENT=${1}
    echo "ENVIRONMENT :::>>> ${ENVIRONMENT}"

else
    echo "Usage: . ./kube-land-setup.sh <<ENVIRONMENT>>"
    exit 1
fi

echo "DESTROYING :::>>> Cluster ::: [[[ kube-land ]]]..."

docker run zoobab/kind

kind delete cluster --name kube-land | true

echo "DESTROYED :::>>> Cluster ::: [[[ kube-land ]]]..."

./kube-land-blast.sh ${ENVIRONMENT}

exit 0