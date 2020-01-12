#!/bin/bash

set -e

if [ $# -eq 2 ]
then

    MICROSERVICE=${1}
    echo "MICROSERVICE :::>>> ${MICROSERVICE}"

    VERSION=${2}
    echo "VERSION :::>>> ${VERSION}"

    # Upgrade KUBE Kubernetes Deployment
    # ---------------------------------

    KUBE_HOME_DIR=/opt/mw/app/kube

    echo "Deploying :::>>> ${MICROSERVICE} : ${VERSION} in [[[${bamboo_kube_kube_cluster}]]]!!!"

ssh -o StrictHostKeyChecking=no ${bamboo_kube_admin_host} << EOF

sudo su -

echo "Hurrey on Kubernetes Master!!!"

id

pwd

/opt/mw/app/kube/install/scripts/cicd/deployKUBEKubernetesPod.sh ${bamboo_kube_kube_cluster} ${MICROSERVICE} ${VERSION} ${bamboo_kube_docker_registry_host} ${bamboo_kube_docker_registry_port} ${bamboo_kube_release_type}

if [ "${MICROSERVICE}" = "kube-admin" ]
then
    sleep 30
else
    sleep 5
fi

EOF

    echo "Deployed :::>>> ${MICROSERVICE} : ${VERSION} in [[[${bamboo_kube_kube_cluster}]]]!!!"

elif [ $# -eq 1 ]
then

    MICROSERVICE=${1}
    echo "MICROSERVICE :::>>> ${MICROSERVICE}"

    echo "[[[SKIP]]] Deploying :::>>> ${MICROSERVICE} in [[[${bamboo_kube_kube_cluster}]]]!!!"

else
    echo "Usage: . ./deployKUBE.sh <<MICROSERVICE>> <<VERSION>>"
    exit 1
fi

exit 0
