pkill -f k8dash | true

kind delete cluster --name kube-land | true

kind create cluster --name kube-land --config kube-land-config.xml

kubectl create -f kube-land-ui-nodeport.yml

sleep 40

kubectl get svc --namespace=kube-system

kubectl get pods --namespace=kube-system -o wide

k8dash=$(kubectl get pods --namespace=kube-system -o wide | awk '{print $1}' | grep k8dash)
echo "K8Dash Pod --> $k8dash"

kubectl port-forward $k8dash 8000:4654 --namespace=kube-system &

./kube-land-ui-access-key.sh



