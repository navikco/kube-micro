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

KUBE_HOME_DIR=/opt/mw/app/kube

echo "KUBE_HOME_DIR ::: ${KUBE_HOME_DIR}"
ls -R ${KUBE_HOME_DIR}/

cd ${KUBE_HOME_DIR}/

mkdir -p ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/

PROFILE=${PROFILE} install/scripts/kube-templater.sh install/templates/kube-namespace.yml > ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/kube-${PROFILE}.yml

cat ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/kube-${PROFILE}.yml

kubectl create -f ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/kube-${PROFILE}.yml

kubectl get namespaces

kubectl describe namespace kube-${PROFILE}

exit 0
