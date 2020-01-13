EWS Losts
===================================

[![Build Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWCD)](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWCD)
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)


- Losts is a Microservice under Enterprise Webservices Platform (EWS)
- It is Spring Boot Application uses Gradle and Rest API Doc
- It packages as Executable JAR


## EWS Losts Resources


| **Useful URLs**	|
| ------------- |
| [Nexus Repo](http://es-nexus01.dal.securustech.net/content/repositories/releases/net/securustech/ews/ews-losts/ "Official Nexus Artifactory for EWS Losts")      	|
| [Git Repo](http://es-bitbucket01.dal.securustech.net/projects/MID/repos/ews-losts/browse "Official Git Repo for EWS Losts")      	|
| [Postman JSON](http://es-bitbucket01.dal.securustech.net/projects/MID/repos/ews-losts/browse/json_tests "Postman JSON for EWS Losts")      	|
| [H2 In-memory DB - LOCAL](http://localhost:20036/ "H2 In-Memory DB Web Console for Local Testing")      	|
| [EWS Admin Console - DEV](http://ld-midsrvcs01.lab.securustech.net:8761/#/ "EWS Admin Console - DEV Environment")      	|
| [Rest API Doc - LOCAL](http://localhost:20026/ews/losts/info/index.html "EWS Losts Rest API Doc - Local Environment")      	|
| [Rest API Doc - DEV](http://ld-midsrvcs01.lab.securustech.net:8761/losts/info/index.html "EWS Losts Rest API Doc - DEV Environment")      	|
     	|


## EWS Losts CI/CD


| Job        | Type (Auto/Manual)	| Status  |Description  |
| ------------- |:-------------:| -----:|-----:|
| [**EWS Losts Ghosts CI**](http://es-compile01.dal.securustech.net/browse/EP-EWCD "EWS Losts CI Job")      | Auto | ![Build Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWCD)	| It triggers automatically when the Code changes are merged to the DEVELOP Branch.	|
| [**EWS Losts CI**](http://es-compile01.dal.securustech.net/browse/EP-EWCD "EWS Losts CI Job")      | Auto | ![Build Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWCD)	| It triggers automatically when the Code changes are merged to the DEVELOP Branch.	|
| [**EWS Losts DEV Release**](http://es-compile01.dal.securustech.net/browse/EP-EWSDR "EWS Losts Deploy to DEV")      | Manual | ![Deploy Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWSDR)	| It needs to be manually triggered once TeamForge Artifacts are Tagged and Release Notes URL is Verified. **release_version, next_version and other individual Apps being released need to be set** in Bamboo Plan Configuration prior to running this Job. |
| [**EWS Losts RUNWAY**](http://es-compile01.dal.securustech.net/browse/EP-EWLR "EWS Losts Deploy to NEXUS Job")      | Manual | ![Deploy Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWLR)	| This Job is to upload Build, Package and Update the Release Deployables to NEXUS for QA, PP or PROD Deployments. It needs to be manually triggered once TeamForge Artifacts are Tagged and Release Notes URL is Verified. **release_version, next_version and other individual Apps being released need to be set** in Bamboo Plan Configuration prior to running this Job. |
| [**EWS Losts TAKEOFF**](http://es-compile01.dal.securustech.net/browse/EP-EW "EWS Losts Install to QA Environment")      | Manual | ![Deploy Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EW)	| This Job is to download desired Release Deployables from NEXUS to the QA Servers and Install them. It needs to be manually triggered once TeamForge Artifacts are Tagged and Release Notes URL is Verified. **release_version and other individual Apps being released need to be set** in Bamboo Plan Configuration prior to running this Job. |

## Available Profiles to run EWS Losts Apps


| EWS Losts Profile        | Embedded/Externalized           | Description           |
| ------------- |:-------------:|:-------------:|
| **local**      | Embedded In the Code    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **dev**      | Externalized on the Server    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **ews_dev**      | Embedded In the Code    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **qa**      | Externalized on the Server    | Uses QA Environment of DB, Kafka and other External Systems. |
| **prod**      | N/A |


## Build And Run ESP Apps

```shell

	cd ews-losts/
	./gradlew clean build
	java -jar -Dspring.profiles.active=local build/libs/ews-losts-1.0.0-SNAPSHOT.jar


``` 


## Test it out 

* **Import** ews-losts/json_tests/Losts.postman_collection.json into your POSTMAN
* **Fire up** the Postman Requests for EWS Losts Endpoints 
* **Verify** the Service Response in Postman



