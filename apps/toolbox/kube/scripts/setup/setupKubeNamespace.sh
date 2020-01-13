#!/bin/bash

set -e

if [ $# -eq 1 ]
then

    PROFILE=${1}
    echo "PROFILE :::>>> ${PROFILE}"

else
    echo "Usage: . ./setupKUBENamespace.sh <<PROFILE>>"
    exit 1
fi

# Create KUBE Kubernetes Namespace
# -------------------------------

KUBE_LAND_DIR=/opt/mw/app/kube

echo "KUBE_LAND_DIR ::: ${KUBE_LAND_DIR}"
ls -lR ${KUBE_LAND_DIR}/

cd ${KUBE_LAND_DIR}/

mkdir -p ${KUBE_LAND_DIR}/cluster/kube-${PROFILE}/

PROFILE=${PROFILE} install/install/scripts/kube-templater.sh install/templates/kube-namespace.yml > ${KUBE_LAND_DIR}/cluster/kube-${PROFILE}/kube-${PROFILE}.yml

cat ${KUBE_LAND_DIR}/cluster/kube-${PROFILE}/kube-${PROFILE}.yml

kubectl create -f ${KUBE_LAND_DIR}/cluster/kube-${PROFILE}/kube-${PROFILE}.yml

kubectl get namespaces

kubectl describe namespace kube-${PROFILE}

exit 0
