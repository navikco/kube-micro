Kube Admin
===================================

[![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)


- Admin is a Spring Boot Admin Console that provides visibility into running Spring Boot Applications
- It offers ability to look into Registered Applications Health, LIVE LOGs, Resources, Environmental Properties etc.
- It is Spring Boot Application uses Gradle and Rest API Doc
- It uses EUREKA Registry to discover the Running Spring Boot Applications (Kube Land Microservices)
- It also uses NETFLIX ZUUL to provide standard PROXY via common PORT (8761) to Individual Microservices Ports (Dynamically assigned)
- It packages as Executable JAR


## Kube Land Admin Resources


| **Useful URLs**	|
| ------------- |
| [DockerHub](https://hub.docker.com/repository/docker/navikco/admin/ "Official DockerHub Artifactory for Kube Land admin")      	|
| [GitHub](https://github.com/navikco/kube/tree/master/apps/admin "Official Git Repo for Kube Land admin")      	|
| [Kube Land Admin Console](http://localhost:8761/admin/wallboard "Kube Land Admin Console")      	|


## Kube Land Admin CI/CD


| Job        | Type (Auto/Manual)	| Status  |Description  |
| ------------- |:-------------:| -----:|-----:|
| [**Kube Land CI**](https://circleci.com/gh/navikco/kube "Kube Land CI Job")      | Auto | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| It triggers automatically when the Code changes are merged to the master Branch	|
| [**Kube Land CD**](https://circleci.com/gh/navikco/kube "Kube Land CD Job")      | Auto | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| It triggers automatically when the Code changes are merged to the master Branch	|
| [**Kube Land Init**](https://circleci.com/gh/navikco/kube "Kube Land Init Job")      | Manual | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| Run it manually to Initialize Kube Land Cluster |
| [**Kube Land Destroy**](https://circleci.com/gh/navikco/kube "Kube Land Destroy Job")      | Manual | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| Run it manually to Destroy Kube Land Cluster |
| [**Kube Land Scale**](https://circleci.com/gh/navikco/kube "Kube Land Scale Job")      | Manual | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| Run it manually to Scale Up or Down Any Kube Land Cluster Resource/Microservice |


## Available Profiles to run Kube Land Admin Apps


| Kube Land Accounts Profile        | Embedded/Externalized           | Description           |
| ------------- |:-------------:|:-------------:|
| **local**      | Embedded In the Code    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **dev**      | Externalized on the Server    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **qa**      | Externalized on the Server    | Uses QA Environment of DB, Kafka and other External Systems. |



## Build And Run Kube Land Admin App

```shell

##Build
	cd admin/
	./gradlew clean build

##Run in LOCAL Profile
	java -jar -Dspring.profiles.active=local build/libs/admin-1.0.0-SNAPSHOT.jar

##Run in Kube Land DEV Profile
	java -jar -Dspring.profiles.active=dev build/libs/admin-1.0.0-SNAPSHOT.jar

##Run in QA Profile
    java -Xms512m -Xmx1024m -Dspring.profiles.active=qa build/libs/admin-1.0.0-SNAPSHOT.jar


``` 


## Test it out 

* **Browse** the SBA (Spring Boot ADMIN) Console with the URL mentioned in 'Kube Land Admin Resources" section above
* **Verify** the MicroServices (Including ADMIN itself) is registered on SBA Console and you can TAIL LOG and Look at Details of the Microservice(s) registered



