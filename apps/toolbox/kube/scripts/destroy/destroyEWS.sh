#!/bin/bash

set -e

id 

ssh -o StrictHostKeyChecking=no ${bamboo_kube_admin_host} << EOF

sudo su -

echo "Hurrey on Kubernetes Master!!!"

id

pwd

/opt/mw/app/kube/install/scripts/destroy/destroyKUBEKubeCluster.sh ${bamboo_kube_kube_cluster}

EOF

exit 0