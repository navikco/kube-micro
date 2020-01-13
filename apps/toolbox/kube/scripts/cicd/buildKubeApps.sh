#!/bin/bash

set -e

echo "START @@@ KUBE>>>DOCKER>>>KUBERNETES ::: CURRENT DIRECTORY :::>>> ${bamboo_working_directory}"
ls -al ${bamboo_working_directory}

echo "KUBE>>>DOCKER>>>KUBERNETES ::: Running on BAMBOO HOST :::>>> ${HOSTNAME}"

for kubeDir in apps/* ; do

    echo "KUBE>>>DOCKER>>>KUBERNETES ::: KUBE Microservice Directory ::: $kubeDir"

    kubeRelease=yes

    if [ "$kubeDir" == "toolbox" ]
    then
        kubeRelease=no
        continue
    fi

    eval "isReleasing=\${bamboo_${kubeDirTrim}}"
    echo "$kubeDir :::>>> Evaluated isReleasing <<<${isReleasing}>>>"

    if [ "$isReleasing" == "yes"  ]
    then

        echo "$kubeDir :::>>> Build Microservice <<<$kubeVersion>>>"

        kubeWorkingDirectory="${bamboo_working_directory}/$kubeDir/"

        cd "${kubeWorkingDirectory}"

        while read line; do
            if  echo "$line" | grep -q "version="; then
                echo "$kubeDir :::>>> Evaluated GRADLE kubeVersion <<<${line}>>>";
                kubeVersion=${line:8}
            fi
        done < "gradle.properties"

        echo "$kubeDir :::>>> Evaluated kubeVersion <<<${kubeVersion}>>>"

        ./gradlew clean build -no-daemon -Prelease.releaseVersion=${kubeVersion}_${bamboo_buildNumber}

        echo "$kubeDir :::>>> Built KUBE Microservice DOCKER Image <<<$kubeVersion>>>"

        if [ "${bamboo_kube_docker_publish}" = "yes" ]
        then

            echo "$kubeDir :::>>> Publish KUBE Microservice Image on Docker Registry <<<$kubeVersion>>>"

            kubeDockerWorkingDirectory="${bamboo_working_directory}/toolbox/kube/docker/build/"

            cp ${kubeWorkingDirectory}/build/libs/*.jar ${kubeDockerWorkingDirectory}/

            kubeKubeDirectory="${bamboo_working_directory}/toolbox/kube/scripts/cicd"

            ${kubeKubeDirectory}/publishKubeDockerImage.sh ${bamboo_working_directory} ${kubeDirTrim} ${kubeVersion}_${bamboo_buildNumber} ${bamboo_kube_docker_registry_host} ${bamboo_kube_docker_registry_port} ${bamboo_kube_release_type}

            echo "$kubeDir :::>>> Published KUBE Microservice Image on Docker Registry <<<$kubeVersion>>>"

        else
            echo "$kubeDir :::>>> [[[SKIP]]] Publish KUBE Microservice Image on Docker Registry <<<$kubeVersion>>>"
        fi

    else
        kubeVersion=${isReleasing}
        kubeRelease=no
        echo "$kubeDir :::>>> [[[SKIP]]] Build Microservice <<<$kubeVersion>>>"
    fi

    if [ "${bamboo_kube_docker_deploy}" = "yes" ] && [ "$kubeRelease" == "yes" ]
    then

        echo "$kubeDir :::>>> Deploy KUBE Microservice Pod on Kubernetes Cluster <<<$kubeVersion>>>"

ssh -o StrictHostKeyChecking=no ${bamboo_kube_admin_host} << EOF

sudo su -

echo "Hurrey on Kubernetes Master!!!"

id

pwd

/opt/mw/app/kube/scripts/cicd/deployKubePod.sh ${bamboo_kube_kube_cluster} ${kubeDirTrim} ${kubeVersion}_${bamboo_buildNumber} ${bamboo_kube_docker_registry_host} ${bamboo_kube_docker_registry_port} ${bamboo_kube_release_type}

EOF

        echo "$kubeDir :::>>> Deployed KUBE Microservice Pod on Kubernetes Cluster <<<$kubeVersion>>>"

    else
        echo "$kubeDir :::>>> [[[SKIP]]] Deploy KUBE Microservice DOCKER Image <<<$kubeVersion>>>"
    fi

done

echo "END @@@ KUBE>>>DOCKER>>>KUBERNETES ::: CURRENT DIRECTORY :::>>> ${bamboo_working_directory}"

exit 0
