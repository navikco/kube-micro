
echo "DELETING :::>>> UI ::: [[[ K8Dash ]]]..."

kubectl delete -f ../cluster/kube-land-ui-nodeport.yml | true

sleep 20

echo "DELETED :::>>> UI ::: [[[ K8Dash ]]]..."

echo "CREATING :::>>> UI ::: [[[ K8Dash ]]]..."

kubectl create -f ../cluster/kube-land-ui-nodeport.yml

sleep 40

echo "CREATED :::>>> UI ::: [[[ K8Dash ]]]..."

kubectl get svc --namespace=kube-system

kubectl get pods --namespace=kube-system -o wide

k8dash=$(kubectl get pods --namespace=kube-system -o wide | awk '{print $1}' | grep k8dash)
echo "K8Dash Pod --> $k8dash"

