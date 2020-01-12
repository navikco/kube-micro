#!/bin/bash

set -e

echo "###Setup kubeadmin User###"

#groupadd kube

useradd -ms /bin/bash kubeadmin
#usermod -G kube kubeadmin

echo "p@ss10n" | passwd --stdin kubeadmin
#echo "kubeadmin ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers
id kubeadmin
