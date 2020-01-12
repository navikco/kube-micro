#!/bin/bash

set -e

id 

ssh -o StrictHostKeyChecking=no ${bamboo_kube_admin_host} << EOF

sudo su -

echo "Hurrey on Kubernetes Master!!!"

id

pwd

/opt/mw/app/kube/install/scripts/scale/scaleKUBEResources.sh ${bamboo_kube_kube_cluster} ${bamboo_kube_microservice} ${bamboo_kube_microservice_replicas} ${bamboo_kube_microservice_resources_limits} ${bamboo_kube_microservice_resources_requests}

EOF