#!/bin/bash

set -e

if [ $# -eq 1 ]
then

    PROFILE=${1}
    echo "PROFILE :::>>> ${PROFILE}"

else
    echo "Usage: . ./setupKUBENFSMount.sh <<PROFILE>>"
    exit 1
fi

# Create KUBE Kubernetes Namespace
# -------------------------------

KUBE_HOME_DIR=/opt/mw/app/kube

echo "KUBE_HOME_DIR ::: ${KUBE_HOME_DIR}"
ls -R ${KUBE_HOME_DIR}/

cd ${KUBE_HOME_DIR}/

mkdir -p ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}

PROFILE=${PROFILE} install/scripts/kube-templater.sh install/templates/kube-nfs.yml -f install/properties/kube-nfs.properties > ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/kube-${PROFILE}-nfs.yml

cat ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/kube-${PROFILE}-nfs.yml

kubectl create -f ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/kube-${PROFILE}-nfs.yml

PROFILE=${PROFILE} install/scripts/kube-templater.sh install/templates/kube-nfs-claim.yml -f install/properties/kube-nfs.properties > ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/kube-${PROFILE}-nfs-claim.yml

cat ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/kube-${PROFILE}-nfs-claim.yml

kubectl create -f ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/kube-${PROFILE}-nfs-claim.yml

kubectl get persistentVolumes --namespace=kube-${PROFILE}

kubectl describe persistentVolume kube-${PROFILE}-nfs --namespace=kube-${PROFILE}

exit 0
