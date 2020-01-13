EWS Categories
===================================

[![Build Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWCD)](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWCD)
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)


- Categories is a Microservice under Enterprise Webservices Platform (EWS)
- It is Spring Boot Application uses Gradle and Rest API Doc
- It packages as Executable JAR


## EWS Categories Resources


| **Useful URLs**	|
| ------------- |
| [Nexus Repo](http://es-nexus01.dal.securustech.net/content/repositories/releases/net/securustech/ews/ews-categories/ "Official Nexus Artifactory for EWS Categories")      	|
| [Git Repo](http://es-bitbucket01.dal.securustech.net/projects/MID/repos/ews-categories/browse "Official Git Repo for EWS Categories")      	|
| [Postman JSON](http://es-bitbucket01.dal.securustech.net/projects/MID/repos/ews-categories/browse/json_tests "Postman JSON for EWS Categories")      	|
| [H2 In-memory DB - LOCAL](http://localhost:20036/ "H2 In-Memory DB Web Console for Local Testing")      	|
| [EWS Admin Console - DEV](http://ld-midsrvcs01.lab.securustech.net:8761/#/ "EWS Admin Console - DEV Environment")      	|
| [Rest API Doc - LOCAL](http://localhost:20026/ews/categories/info/index.html "EWS Categories Rest API Doc - Local Environment")      	|
| [Rest API Doc - DEV](http://ld-midsrvcs01.lab.securustech.net:8761/categories/info/index.html "EWS Categories Rest API Doc - DEV Environment")      	|
     	|


## EWS Categories CI/CD


| Job        | Type (Auto/Manual)	| Status  |Description  |
| ------------- |:-------------:| -----:|-----:|
| [**EWS Categories Ghosts CI**](http://es-compile01.dal.securustech.net/browse/EP-EWCD "EWS Categories CI Job")      | Auto | ![Build Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWCD)	| It triggers automatically when the Code changes are merged to the DEVELOP Branch.	|
| [**EWS Categories CI**](http://es-compile01.dal.securustech.net/browse/EP-EWCD "EWS Categories CI Job")      | Auto | ![Build Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWCD)	| It triggers automatically when the Code changes are merged to the DEVELOP Branch.	|
| [**EWS Categories DEV Release**](http://es-compile01.dal.securustech.net/browse/EP-EWSDR "EWS Categories Deploy to DEV")      | Manual | ![Deploy Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWSDR)	| It needs to be manually triggered once TeamForge Artifacts are Tagged and Release Notes URL is Verified. **release_version, next_version and other individual Apps being released need to be set** in Bamboo Plan Configuration prior to running this Job. |
| [**EWS Categories RUNWAY**](http://es-compile01.dal.securustech.net/browse/EP-EWLR "EWS Categories Deploy to NEXUS Job")      | Manual | ![Deploy Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWLR)	| This Job is to upload Build, Package and Update the Release Deployables to NEXUS for QA, PP or PROD Deployments. It needs to be manually triggered once TeamForge Artifacts are Tagged and Release Notes URL is Verified. **release_version, next_version and other individual Apps being released need to be set** in Bamboo Plan Configuration prior to running this Job. |
| [**EWS Categories TAKEOFF**](http://es-compile01.dal.securustech.net/browse/EP-EW "EWS Categories Install to QA Environment")      | Manual | ![Deploy Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EW)	| This Job is to download desired Release Deployables from NEXUS to the QA Servers and Install them. It needs to be manually triggered once TeamForge Artifacts are Tagged and Release Notes URL is Verified. **release_version and other individual Apps being released need to be set** in Bamboo Plan Configuration prior to running this Job. |

## Available Profiles to run EWS Categories Apps


| EWS Categories Profile        | Embedded/Externalized           | Description           |
| ------------- |:-------------:|:-------------:|
| **local**      | Embedded In the Code    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **dev**      | Externalized on the Server    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **ews_dev**      | Embedded In the Code    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **qa**      | Externalized on the Server    | Uses QA Environment of DB, Kafka and other External Systems. |
| **prod**      | N/A |


## Build And Run ESP Apps

```shell

	cd ews-categories/
	./gradlew clean build
	java -jar -Dspring.profiles.active=local build/libs/ews-categories-1.0.0-SNAPSHOT.jar


``` 


## Test it out 

* **Import** ews-categories/json_tests/Categories.postman_collection.json into your POSTMAN
* **Fire up** the Postman Requests for EWS Categories Endpoints 
* **Verify** the Service Response in Postman



