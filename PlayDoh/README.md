Kube Land Accounts
===================================

[![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)


- Accounts is a Microservice under Kube Land Demos Workspace
- It is Spring Boot Application uses Gradle and Rest API Doc
- It packages as Executable JAR and builds Docker Image
- It runs on Kubernetes Cloud


## Kube Land Accounts Resources


| **Useful URLs**	|
| ------------- |
| [DockerHub](https://hub.docker.com/repository/docker/navikco/accounts/ "Official DockerHub Artifactory for Kube Land Accounts")      	|
| [GitHub](https://github.com/navikco/kube/tree/master/apps/accounts "Official Git Repo for Kube Land Accounts")      	|
| [Postman JSON](https://github.com/navikco/kube/tree/master/apps/accounts/browse/json_tests "Postman JSON for Kube Land Accounts")      	|
| [Kube Land Admin Console](http://localhost:8761/admin/wallboard "Kube Land Admin Console")      	|
| [Rest API Doc](http://localhost:8761/kube/accounts/info/index.html "Kube Land Accounts Rest API Doc - Local Environment")      	|
| [CircleCI](https://circleci.com/gh/navikco/kube "Kube Land - CI/CD")      	|

## Kube Land Accounts CI/CD


| Job        | Type (Auto/Manual)	| Status  |Description  |
| ------------- |:-------------:| -----:|-----:|
| [**Kube Land CI**](https://circleci.com/gh/navikco/kube "Kube Land CI Job")      | Auto | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| It triggers automatically when the Code changes are merged to the master Branch	|
| [**Kube Land CD**](https://circleci.com/gh/navikco/kube "Kube Land CD Job")      | Auto | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| It triggers automatically when the Code changes are merged to the master Branch	|
| [**Kube Land Init**](https://circleci.com/gh/navikco/kube "Kube Land Init Job")      | Manual | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| Run it manually to Initialize Kube Land Cluster |
| [**Kube Land Destroy**](https://circleci.com/gh/navikco/kube "Kube Land Destroy Job")      | Manual | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| Run it manually to Destroy Kube Land Cluster |
| [**Kube Land Scale**](https://circleci.com/gh/navikco/kube "Kube Land Scale Job")      | Manual | [![CircleCI](https://circleci.com/gh/navikco/kube.svg?style=svg)](https://circleci.com/gh/navikco/kube)	| Run it manually to Scale Up or Down Any Kube Land Cluster Resource/Microservice |


## Available Profiles to run Kube Land Accounts Apps


| Kube Land Accounts Profile        | Embedded/Externalized           | Description           |
| ------------- |:-------------:|:-------------:|
| **local**      | Embedded In the Code    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **dev**      | Externalized on the Server    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **qa**      | Externalized on the Server    | Uses QA Environment of DB, Kafka and other External Systems. |



## Build And Run Microservice App

```shell

	cd accounts/
	./gradlew clean build
	java -jar -Dspring.profiles.active=local build/libs/accounts-1.0.0-SNAPSHOT.jar


``` 


## Test it out 

* **Import** accounts/json_tests/Accounts.postman_collection.json into your POSTMAN
* **Fire up** the Postman Requests for Kube Land Accounts Endpoints 
* **Verify** the Service Response in Postman



