k8dash=$(kubectl get pods --namespace=kube-system -o wide | awk '{print $1}' | grep k8dash)
echo "K8Dash Pod --> $k8dash"

k8dashSecrets=$(kubectl get secrets | awk '{print $1}' | grep k8dash)
echo "K8Dash Secrets --> $k8dashSecrets"

for d in ../apps/* ; do
    echo "$d"
    echo "KUBE>>>DOCKER>>>KUBERNETES ::: KUBE Domain ::: ${d:8}"
done
