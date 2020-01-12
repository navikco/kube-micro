#!/bin/bash

set -e

if [ $# -eq 1 ]
then

    PROFILE=${1}
    echo "PROFILE :::>>> ${PROFILE}"

else
    echo "Usage: . ./destroyKUBEKubeCluster.sh <<PROFILE>>"
    exit 1
fi

# Upgrade KUBE Kubernetes Deployment
# ---------------------------------

KUBE_HOME_DIR=/opt/mw/app/kube

echo "KUBE_HOME_DIR ::: ${KUBE_HOME_DIR}"
ls -R ${KUBE_HOME_DIR}/

cd ${KUBE_HOME_DIR}/

kubectl get services --namespace=kube-${PROFILE}
kubectl delete services --namespace=kube-${PROFILE} --all
kubectl get services --namespace=kube-${PROFILE}

kubectl get deployments --namespace=kube-${PROFILE}
kubectl delete deployment --namespace=kube-${PROFILE} --all
kubectl get deployments --namespace=kube-${PROFILE}

kubectl get persistentVolumes --namespace=kube-${PROFILE}
kubectl describe persistentVolume kube-${PROFILE}-nfs --namespace=kube-${PROFILE}
timeout 10s kubectl delete persistentVolume kube-${PROFILE}-nfs --namespace=kube-${PROFILE} | true
kubectl patch persistentVolume kube-${PROFILE}-nfs -p '{"metadata":{"finalizers":null}}' --namespace=kube-${PROFILE}
kubectl get persistentVolumes --namespace=kube-${PROFILE}

kubectl get namespaces
kubectl delete namespace kube-${PROFILE}
kubectl get namespaces

rm -rf ${KUBE_HOME_DIR}/cluster/kube-${PROFILE}/

exit 0
