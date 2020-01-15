# Kube Land Demos

_**Run Kubernetes Cluster Locally**_

`git clone https://github.com/navikco/kube.git`

`cd kube/setup/`

`chmod 700 kube-land.sh`

`./kube-land.sh    `

- This would start Docker Containers for Kubernetes Kind (https://github.com/kubernetes-sigs/kind)

- It would also start the Kubernetes Pod for K8Dash UI Dashboard

- Once the Command exits with success,
    - You should be able to execute any **kubectl** Commands
    
    - To Check your Local Kubernetes Cluster,
    
        `kubectl get nodes`
     
    - You can Browse your K8Dash UI on --> 
    
        `http://localhost:8000/#!`

`cd kube/setup/cluster/kube-green/`

- To Deploy Test Apps/Microservices,
    - Run the **kubectl** commands below,
    
    `kubectl create -f kube-green.yml`   (Creates Kubernetes Namespace)
          
    `kubectl get namespaces`  (Should list **kube-green** Namespace)
    
    `kubectl create -f admin-service.yml`  (Creates & Exposes **admin** Microservice as Kubernetes Service)
    
    `kubectl get services --namespace=kube-green`
    
    `kubectl create -f admin/admin-deployment.yml`  (Creates **admin** Microservice Deployment)
    
    `kubectl get pods --namespace=kube-green`
    
    `kubectl create -f accounts/accounts-deployment.yml`  (Creates **accounts** Microservice Deployment)
    
    `kubectl get pods --namespace=kube-green`

- Verify Microservices Deployment in your Local Kubernetes Cluster

    - Spring Boot Admin Console URL 
    
        `http://10.70.245.142:30333/admin/wallboard`
        
    - Microservice Endpoint
     
        `curl 'http://localhost:30333/kube/accounts' -i -X GET -H 'Accept: application/json'`



_**LOCALLY Build and Run Microservices Docker Images**_

- Build & Run the **admin** Microservice first,
    
`cd apps/admin/`

`./gradlew clean build`

`cd apps/toolbox/kube/docker/`

`./blastKube.sh green admin 8761 1.1.1` (Build & Run Microservice Docker Image)

- Access **admin** Microservice on 
 
http://localhost:8761/admin/wallboard

 
- Build the **OTHER** Microservices now,

   
`cd apps/account/`  (You can change to any other Microservice here!)

`./gradlew clean build`

`cd apps/toolbox/kube/docker/`

`./blastKube.sh green account 8080 1.1.1` (Build & Run Microservice Docker Image - Change Port for subsequent Microservices)

- Verify all **OTHER** Microservices on 
 
http://localhost:8761/admin/wallboard
