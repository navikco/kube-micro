#!/bin/bash

set -e

echo "START @@@ KUBE>>>DOCKER>>>KUBERNETES ::: CURRENT DIRECTORY :::>>> ${bamboo_working_directory}"
ls -al ${bamboo_working_directory}

#Convert $MICROSERVICE to UNIX Format - i.e. kube-lookups to kube_lookups
convertToUnix() {

    echo "${kubeDirTrim}" | tr - _
}

echo "KUBE>>>DOCKER>>>KUBERNETES ::: Running on BAMBOO HOST :::>>> ${HOSTNAME}"

for kubeDir in */ ; do

    echo "KUBE>>>DOCKER>>>KUBERNETES ::: KUBE Microservice Directory ::: $kubeDir"

    kubeRelease=yes

    kubeDirTrim=${kubeDir%?}
    echo "KUBE>>>DOCKER>>>KUBERNETES ::: KUBE Domain ::: ${kubeDirTrim:4}"

    if [ "$kubeDirTrim" == "kube-toolbox" ] || [ "$kubeDirTrim" == "artifacts" ]
    then
        kubeRelease=no
        continue
    fi

    kubeDirTrimUnix="$(convertToUnix ${kubeDirTrim})"
    echo "$kubeDirTrim :::>>> Evaluated kubeDirTrimUnix <<<${kubeDirTrimUnix}>>>"

    eval "isReleasing=\${bamboo_${kubeDirTrimUnix}}"
    echo "$kubeDirTrim :::>>> Evaluated isReleasing <<<${isReleasing}>>>"

    if [ "$isReleasing" == "yes"  ]
    then

        echo "$kubeDirTrim :::>>> Build Microservice <<<$kubeVersion>>>"

        kubeWorkingDirectory="${bamboo_working_directory}/$kubeDirTrim/"

        cd "${kubeWorkingDirectory}"

        while read line; do
            if  echo "$line" | grep -q "version="; then
                echo "$kubeDirTrim :::>>> Evaluated GRADLE kubeVersion <<<${line}>>>";
                kubeVersion=${line:8}
            fi
        done < "gradle.properties"

        echo "$kubeDirTrim :::>>> Evaluated kubeVersion <<<${kubeVersion}>>>"

        ./gradlew clean build sonarqube --no-daemon -Prelease.releaseVersion=${kubeVersion}_${bamboo_buildNumber}

        echo "$kubeDirTrim :::>>> Built KUBE Microservice DOCKER Image <<<$kubeVersion>>>"

        if [ "${bamboo_kube_docker_publish}" = "yes" ]
        then

            echo "$kubeDirTrim :::>>> Publish KUBE Microservice Image on Docker Registry <<<$kubeVersion>>>"

            kubeDockerWorkingDirectory="${bamboo_working_directory}/kube-toolbox/kube/docker/build/"

            cp ${kubeWorkingDirectory}/build/libs/*.jar ${kubeDockerWorkingDirectory}/

            kubeKubeDirectory="${bamboo_working_directory}/kube-toolbox/kube/scripts/cicd"

            ${kubeKubeDirectory}/publishKUBEDockerImage.sh ${bamboo_working_directory} ${kubeDirTrim} ${kubeVersion}_${bamboo_buildNumber} ${bamboo_kube_docker_registry_host} ${bamboo_kube_docker_registry_port} ${bamboo_kube_release_type}

            echo "$kubeDirTrim :::>>> Published KUBE Microservice Image on Docker Registry <<<$kubeVersion>>>"

        else
            echo "$kubeDirTrim :::>>> [[[SKIP]]] Publish KUBE Microservice Image on Docker Registry <<<$kubeVersion>>>"
        fi

    else
        kubeVersion=${isReleasing}
        kubeRelease=no
        echo "$kubeDirTrim :::>>> [[[SKIP]]] Build Microservice <<<$kubeVersion>>>"
    fi

#    if [ ! -z "${isReleasing}" ] && [ "${isReleasing}" != "yes"  ]
#    then
#        kubeVersion=${isReleasing}
#    fi

    if [ "${bamboo_kube_docker_deploy}" = "yes" ] && [ "$kubeRelease" == "yes" ]
    then

        echo "$kubeDirTrim :::>>> Deploy KUBE Microservice Pod on Kubernetes Cluster <<<$kubeVersion>>>"

ssh -o StrictHostKeyChecking=no ${bamboo_kube_admin_host} << EOF

sudo su -

echo "Hurrey on Kubernetes Master!!!"

id

pwd

/opt/mw/app/kube/install/scripts/cicd/deployKUBEKubernetesPod.sh ${bamboo_kube_kube_cluster} ${kubeDirTrim} ${kubeVersion}_${bamboo_buildNumber} ${bamboo_kube_docker_registry_host} ${bamboo_kube_docker_registry_port} ${bamboo_kube_release_type}

EOF

        echo "$kubeDirTrim :::>>> Deployed KUBE Microservice Pod on Kubernetes Cluster <<<$kubeVersion>>>"

    else
        echo "$kubeDirTrim :::>>> [[[SKIP]]] Deploy KUBE Microservice DOCKER Image <<<$kubeVersion>>>"
    fi

done

echo "END @@@ KUBE>>>DOCKER>>>KUBERNETES ::: CURRENT DIRECTORY :::>>> ${bamboo_working_directory}"

exit 0
