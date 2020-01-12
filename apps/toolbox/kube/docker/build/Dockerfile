# LICENSE CDDL 1.0 + GPL 2.0 + SECURUS-MW-KUBE 1.0
#
# KUBE DOCKER
# ----------
# This Dockerfile extends CentOS 7 OpenJDK 8 as Base Image
# It builds KUBE Microservice on top of it with Configurable -
    ## Environment / Active Profile (freeride, green, blue etc.)
    ## Microservice (${kubeMicroservice}, esp-rules etc.)
#
# HOW TO BUILD THIS IMAGE
# -----------------------
# Checkout All KUBE, ESP Microservices AND kube-toolbox under the same PARENT Directory - i.e. /opt/mw/kube/
# Checkout Bitbucket Repo for KUBE/ESP Microservice - http://bitbucket.dal.securustech.net/scm/mid/<<MICROSERVICE>>.git
# Checkout Bitbucket Repo for KUBE-TOOLBOX - http://bitbucket.dal.securustech.net/scm/mid/kube-toolbox.git
#
# Run:
#      $ cd <<PARENT_DIRECTORY>>/kube-toolbox/docker/kube
#	   $ ./blastKUBE.sh green kube-customers 20024 2.0.0-SNAPSHOT_1
#
# Access:
#      # Access your Microservice running as DOCKER Container at
#           https://localhost:20024/kube/customers/info/index.html

# Pull base image
# ---------------
FROM parrotstream/centos-openjdk:8

# Maintainer
# ----------
MAINTAINER MW Kube <kube@navik.co>

ARG kubeMicroservice

# KUBE Setup as Root
USER root
VOLUME ["/opt/mw/mount"]
RUN mkdir -p /opt/mw/app/kube/
WORKDIR /opt/mw/app/kube/
ADD ./ .

RUN ./bin/setupKube.sh ${kubeMicroservice}

# KUBE Docker Container Run as kubeadmin
USER kubeadmin

WORKDIR /opt/mw/app/kube/bin/
#Start KUBE Microservice with Elastic Search
ENTRYPOINT ["/opt/mw/app/kube/bin/startKube.sh"]
