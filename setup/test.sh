
echo $(kubectl get nodes -o jsonpath='{.items[*].status.addresses[?(@.type=="InternalIP")].address}' | awk '{print $1;}')

K8S_MASTER_IP=$(echo $(kubectl get nodes -o jsonpath='{.items[*].status.addresses[?(@.type=="InternalIP")].address}' | awk '{print $1;}'))
echo $K8S_MASTER_IP

find . -type f -name "*.yml" -print0 | xargs -0 sed -i '' -e "s/localhost/${K8S_MASTER_IP}/g"