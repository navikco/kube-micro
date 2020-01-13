#!/bin/bash

set -e

kubeDirTrim=customers

echo "START @@@ KUBE>>>DOCKER>>>KUBERNETES ::: CURRENT DIRECTORY :::>>> ${bamboo_working_directory}"
ls -al ${bamboo_working_directory}

echo "KUBE>>>DOCKER>>>KUBERNETES ::: Running on BAMBOO HOST :::>>> ${HOSTNAME}"

echo "KUBE>>>DOCKER>>>KUBERNETES ::: KUBE Microservice Directory ::: $kubeDir"

echo "$kubeDirTrim :::>>> Build Microservice <<<$kubeVersion>>>"

kubeWorkingDirectory="${bamboo_working_directory}/apps/$kubeDirTrim/"

cd "${kubeWorkingDirectory}"

while read line; do
    if  echo "$line" | grep -q "version="; then
        echo "$kubeDirTrim :::>>> Evaluated GRADLE kubeVersion <<<${line}>>>";
        kubeVersion=${line:8}
    fi
done < "gradle.properties"

echo "$kubeDirTrim :::>>> Evaluated kubeVersion <<<${kubeVersion}>>>"

./gradlew clean build -no-daemon -Prelease.releaseVersion=${kubeVersion}_${bamboo_buildNumber}

echo "$kubeDirTrim :::>>> Built KUBE Microservice DOCKER Image <<<$kubeVersion>>>"

echo "$kubeDirTrim :::>>> Publish KUBE Microservice Image on Docker Registry <<<$kubeVersion>>>"

kubeDockerWorkingDirectory="${bamboo_working_directory}/apps/toolbox/kube/docker/build/"

cp ${kubeWorkingDirectory}/build/libs/*.jar ${kubeDockerWorkingDirectory}/

kubeDirectory="${bamboo_working_directory}/apps/toolbox/kube/scripts/cicd"

${kubeDirectory}/publishKubeDockerImage.sh ${bamboo_working_directory} ${kubeDirTrim} ${kubeVersion}_${bamboo_buildNumber} ${bamboo_kube_docker_registry_host}

echo "$kubeDirTrim :::>>> Published KUBE Microservice Image on Docker Registry <<<$kubeVersion>>>"

echo "$kubeDirTrim :::>>> Deploy KUBE Microservice Pod on Kubernetes Cluster <<<$kubeVersion>>>"

ssh -o StrictHostKeyChecking=no ${bamboo_kube_admin_host} << EOF

sudo su -

echo "Hurrey on Kubernetes Master!!!"

id

pwd

${kubeDirectory}/deployKubePod.sh ${bamboo_kube_cluster} ${kubeDirTrim} ${kubeVersion}_${bamboo_buildNumber} ${bamboo_kube_docker_registry_host}

EOF

echo "$kubeDirTrim :::>>> Deployed KUBE Microservice Pod on Kubernetes Cluster <<<$kubeVersion>>>"

echo "END @@@ KUBE>>>DOCKER>>>KUBERNETES ::: CURRENT DIRECTORY :::>>> ${bamboo_working_directory}"

exit 0
