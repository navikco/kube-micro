#!/bin/bash

set -e

if [ $# -eq 6 ]
then

    PROFILE=${1}
    echo "PROFILE :::>>> ${PROFILE}"

    MICROSERVICE=${2}
    echo "MICROSERVICE :::>>> ${MICROSERVICE}"

    VERSION=${3}
    echo "VERSION :::>>> ${VERSION}"

    DOCKER_REGISTRY_HOST=${4}
    echo "DOCKER_REGISTRY_HOST :::>>> ${DOCKER_REGISTRY_HOST}"

    DOCKER_REGISTRY_PORT=${5}
    echo "DOCKER_REGISTRY_PORT :::>>> ${DOCKER_REGISTRY_PORT}"

    DOCKER_RELEASE_TYPE=${6}
    echo "DOCKER_RELEASE_TYPE :::>>> ${DOCKER_RELEASE_TYPE}"

    # Upgrade KUBE Kubernetes Deployment
    # ---------------------------------

    KUBE_HOME_DIR=/opt/mw/app/kube

    echo "Deploying :::>>> ${MICROSERVICE} : ${VERSION} in [[[${PROFILE}]]]!!!"

    echo "KUBE_HOME_DIR ::: ${KUBE_HOME_DIR}"
    ls -R ${KUBE_HOME_DIR}/

    cd ${KUBE_HOME_DIR}/

    kubectl set image deployment.v1.apps/${MICROSERVICE}-deployment ${MICROSERVICE}=${DOCKER_REGISTRY_HOST}:${DOCKER_REGISTRY_PORT}/${MICROSERVICE}:${VERSION} --record --namespace=kube-${PROFILE}

    kubectl get deployments --namespace=kube-${PROFILE}

    kubectl describe deployment ${MICROSERVICE}-deployment --namespace=kube-${PROFILE}

    if [ "${MICROSERVICE}" = "kube-admin" ]
    then
        sleep 30
    else
        sleep 5
    fi

    echo "Deployed :::>>> ${MICROSERVICE} : ${VERSION} in [[[${PROFILE}]]]!!!"

else
    echo "Usage: ./deployKUBEKubernetesPod.sh <<PROFILE>> <<MICROSERVICE>> <<VERSION>> <<DOCKER_REGISTRY_HOST>> <<DOCKER_REGISTRY_PORT>> <<DOCKER_RELEASE_TYPE>>"
    exit 1
fi

exit 0
