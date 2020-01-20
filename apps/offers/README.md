Kube Land Offers
===================================

[![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)


- Offers is a Microservice under Enterprise Webservices Platform (Kube Land)
- It is Spring Boot Application uses Gradle and Rest API Doc
- It packages as Executable JAR


## Kube Land Offers Resources


| **Useful URLs**	|
| ------------- |
| [DockerHub](https://hub.docker.com/repository/docker/navikco/offers/ "Official DockerHub Artifactory for Kube Land Offers")      	|
| [GitHub](https://github.com/navikco/kube/tree/master/apps/offers "Official Git Repo for Kube Land Offers")      	|
| [Postman JSON](https://github.com/navikco/kube/tree/master/apps/offers/browse/json_tests "Postman JSON for Kube Land Offers")      	|
| [Kube Land Admin Console](http://localhost:8761/admin/wallboard "Kube Land Admin Console")      	|
| [Rest API Doc](http://localhost:8761/kube/offers/info/index.html "Kube Land Offers Rest API Doc - Local Environment")      	|
| [CircleCI](https://circleci.com/gh/navikco/kube "Kube Land - CI/CD")      	|


## Kube Land Offers CI/CD


| Job        | Type (Auto/Manual)	| Status  |Description  |
| ------------- |:-------------:| -----:|-----:|
| [**Kube Land CI**](https://circleci.com/gh/navikco/kube "Kube Land CI Job")      | Auto | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| It triggers automatically when the Code changes are merged to the master Branch	|
| [**Kube Land CD**](https://circleci.com/gh/navikco/kube "Kube Land CD Job")      | Auto | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| It triggers automatically when the Code changes are merged to the master Branch	|
| [**Kube Land Init**](https://circleci.com/gh/navikco/kube "Kube Land Init Job")      | Manual | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| Run it manually to Initialize Kube Land Cluster |
| [**Kube Land Destroy**](https://circleci.com/gh/navikco/kube "Kube Land Destroy Job")      | Manual | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| Run it manually to Destroy Kube Land Cluster |
| [**Kube Land Scale**](https://circleci.com/gh/navikco/kube "Kube Land Scale Job")      | Manual | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| Run it manually to Scale Up or Down Any Kube Land Cluster Resource/Microservice |


## Available Profiles to run Kube Land Offers Apps


| Kube Land Offers Profile        | Embedded/Externalized           | Description           |
| ------------- |:-------------:|:-------------:|
| **local**      | Embedded In the Code    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **dev**      | Externalized on the Server    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **qa**      | Externalized on the Server    | Uses QA Environment of DB, Kafka and other External Systems. |



## Build And Run Microservice App

```shell

	cd offers/
	./gradlew clean build
	java -jar -Dspring.profiles.active=local build/libs/offers-1.0.0-SNAPSHOT.jar


``` 


## Test it out 

* **Import** offers/json_tests/Offers.postman_collection.json into your POSTMAN
* **Fire up** the Postman Requests for Kube Land Offers Endpoints 
* **Verify** the Service Response in Postman



