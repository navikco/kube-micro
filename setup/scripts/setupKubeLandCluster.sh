#!/bin/sh
set -o errexit

echo "CREATING :::>>> Cluster ::: [[[ kube-land ]]]..."

kind create cluster --name kube-land --config ../cluster/kube-land-config.yml

kind get clusters

echo "CREATED :::>>> Cluster ::: [[[ kube-land ]]]..."