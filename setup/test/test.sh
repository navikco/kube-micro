#!/bin/bash

#echo $(kubectl get nodes -o jsonpath='{.items[*].status.addresses[?(@.type=="InternalIP")].address}' | awk '{print $1;}')

#K8S_MASTER_IP=$(echo $(kubectl get nodes -o jsonpath='{.items[*].status.addresses[?(@.type=="InternalIP")].address}' | awk '{print $1;}'))
#echo $K8S_MASTER_IP

#find . -type f -name "*.yml" -print0 | xargs -0 sed -i '' -e "s/localhost/${K8S_MASTER_IP}/g"

#find . -iname "*green*" -exec rename green ${ENVIRONMENT} '{}' \;
#find . -iname \*.* | rename -v "s/green/${1}/g"

#sed -i 's/green/${ENVIRONMENT}/g' *.yml
find . -type f -name "*.yml" -print0 | xargs -0 sed -i '' -e "s/black/${1}/g"

mv -i test-black test-${1}

for f in $(find . -name "*black*"); do mv $f $(echo $f | sed "s/black/${1}/"); done