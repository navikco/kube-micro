#!/bin/bash

set -e

if [ $# -eq 1 ]
then

    MICROSERVICE=${1}
    echo "MICROSERVICE :::>>> ${MICROSERVICE}"

else
    echo "Usage: . ./setupKubeRuntime.sh <<MICROSERVICE>>"
    exit 1
fi

echo "###Setup KUBE Runtime Environment###"

mkdir -p /opt/mw/app/kube/${MICROSERVICE}/

mkdir -p /opt/mw/mount/logs/kube/${MICROSERVICE}/

mv ${MICROSERVICE}*.jar /opt/mw/app/kube/${MICROSERVICE}/

chown kubeadmin:kubeadmin -R /opt/mw/

chmod 775 -R /opt/mw/

ls -R /opt/mw/
