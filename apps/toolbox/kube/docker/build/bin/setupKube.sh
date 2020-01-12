#!/bin/bash

set -e

if [ $# -eq 1 ]
then

    MICROSERVICE=${1}
    echo "MICROSERVICE :::>>> ${MICROSERVICE}"

else
    echo "Usage: . ./setupKube.sh <<MICROSERVICE>>"
    exit 1
fi

# KUBE Microservice running as Docker Container
# --------------------------------------------
# https://localhost:20024/kube/customers/info/index.html

# KUBE Microservice Docker Container sudo/root Password
# ----------------
# welcome1

echo "welcome1" | passwd --stdin root

echo "PWD ::: ${PWD}"
ls -al ${PWD}

./bin/setupKubeUser.sh

chown kubeadmin:kubeadmin -R /opt/mw/app/kube/

./bin/setupKubeRuntime.sh ${MICROSERVICE}

chmod 775 -R /opt/mw/

echo "ls -R /opt/ ---"
ls -alR /opt/
