#!/bin/bash

set -e

rsync --rsync-path='/usr/bin/sudo /usr/bin/rsync' -rvm -e "ssh -o StrictHostKeyChecking=no" --exclude 'docker' ${bamboo_build_working_directory}/kube-toolbox/kube/ ${bamboo_kube_admin_host}:/opt/mw/app/kube/install/

exit 0