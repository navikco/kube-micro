pkill -f k8dash | true

pkill -f 8761 | true

kind delete cluster --name kube-land | true

kind create cluster --name kube-land --config kube-land-config.xml

kubectl create -f kube-land-ui-nodeport.yml

sleep 40

kubectl get svc --namespace=kube-system

kubectl get pods --namespace=kube-system -o wide

k8dash=$(kubectl get pods --namespace=kube-system -o wide | awk '{print $1}' | grep k8dash)
echo "K8Dash Pod --> $k8dash"

kubectl port-forward $k8dash 8000:4654 --namespace=kube-system &

cd cluster/kube-green/

K8S_MASTER_IP=$(echo $(kubectl get nodes -o jsonpath='{.items[*].status.addresses[?(@.type=="InternalIP")].address}' | awk '{print $1;}'))
echo "Kube Master IP >>> $K8S_MASTER_IP"

#sed -i 's/localhost/${K8S_MASTER_IP}/g' *.yml
find . -type f -name "*.yml" -print0 | xargs -0 sed -i '' -e "s/localhost/${K8S_MASTER_IP}/g"

kubectl create -f kube-green.yml

kubectl get namespaces

kubectl create -f admin-service.yml

kubectl get services --namespace=kube-green

kubectl create -f admin/admin-deployment.yml

kubectl get pods --namespace=kube-green

kubectl create -f accounts/accounts-deployment.yml

kubectl get pods --namespace=kube-green

sleep 120

kubectl port-forward deployment/admin-deployment 8761:8761 --namespace=kube-green &

cd ../../

./kube-land-ui-access-key.sh

