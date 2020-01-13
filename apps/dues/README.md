EWS Dues
===================================

[![Build Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWCD)](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWCD)
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)


- Dues is a Microservice under Enterprise Webservices Platform (EWS)
- It is Spring Boot Application uses Gradle and Rest API Doc
- It packages as Executable JAR


## EWS Dues Resources


| **Useful URLs**	|
| ------------- |
| [Nexus Repo](http://es-nexus01.dal.securustech.net/content/repositories/releases/net/securustech/ews/ews-dues/ "Official Nexus Artifactory for EWS Dues")      	|
| [Git Repo](http://es-bitbucket01.dal.securustech.net/projects/MID/repos/ews-dues/browse "Official Git Repo for EWS Dues")      	|
| [Postman JSON](http://es-bitbucket01.dal.securustech.net/projects/MID/repos/ews-dues/browse/json_tests "Postman JSON for EWS Dues")      	|
| [H2 In-memory DB - LOCAL](http://localhost:20036/ "H2 In-Memory DB Web Console for Local Testing")      	|
| [EWS Admin Console - DEV](http://ld-midsrvcs01.lab.securustech.net:8761/#/ "EWS Admin Console - DEV Environment")      	|
| [Rest API Doc - LOCAL](http://localhost:20026/ews/dues/info/index.html "EWS Dues Rest API Doc - Local Environment")      	|
| [Rest API Doc - DEV](http://ld-midsrvcs01.lab.securustech.net:8761/dues/info/index.html "EWS Dues Rest API Doc - DEV Environment")      	|
     	|


## EWS Dues CI/CD


| Job        | Type (Auto/Manual)	| Status  |Description  |
| ------------- |:-------------:| -----:|-----:|
| [**EWS Dues Ghosts CI**](http://es-compile01.dal.securustech.net/browse/EP-EWCD "EWS Dues CI Job")      | Auto | ![Build Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWCD)	| It triggers automatically when the Code changes are merged to the DEVELOP Branch.	|
| [**EWS Dues CI**](http://es-compile01.dal.securustech.net/browse/EP-EWCD "EWS Dues CI Job")      | Auto | ![Build Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWCD)	| It triggers automatically when the Code changes are merged to the DEVELOP Branch.	|
| [**EWS Dues DEV Release**](http://es-compile01.dal.securustech.net/browse/EP-EWSDR "EWS Dues Deploy to DEV")      | Manual | ![Deploy Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWSDR)	| It needs to be manually triggered once TeamForge Artifacts are Tagged and Release Notes URL is Verified. **release_version, next_version and other individual Apps being released need to be set** in Bamboo Plan Configuration prior to running this Job. |
| [**EWS Dues RUNWAY**](http://es-compile01.dal.securustech.net/browse/EP-EWLR "EWS Dues Deploy to NEXUS Job")      | Manual | ![Deploy Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWLR)	| This Job is to upload Build, Package and Update the Release Deployables to NEXUS for QA, PP or PROD Deployments. It needs to be manually triggered once TeamForge Artifacts are Tagged and Release Notes URL is Verified. **release_version, next_version and other individual Apps being released need to be set** in Bamboo Plan Configuration prior to running this Job. |
| [**EWS Dues TAKEOFF**](http://es-compile01.dal.securustech.net/browse/EP-EW "EWS Dues Install to QA Environment")      | Manual | ![Deploy Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EW)	| This Job is to download desired Release Deployables from NEXUS to the QA Servers and Install them. It needs to be manually triggered once TeamForge Artifacts are Tagged and Release Notes URL is Verified. **release_version and other individual Apps being released need to be set** in Bamboo Plan Configuration prior to running this Job. |

## Available Profiles to run EWS Dues Apps


| EWS Dues Profile        | Embedded/Externalized           | Description           |
| ------------- |:-------------:|:-------------:|
| **local**      | Embedded In the Code    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **dev**      | Externalized on the Server    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **ews_dev**      | Embedded In the Code    | Uses Dev Environment of DB, Kafka and other External Systems. |
| **qa**      | Externalized on the Server    | Uses QA Environment of DB, Kafka and other External Systems. |
| **prod**      | N/A |


## Build And Run ESP Apps

```shell

	cd ews-dues/
	./gradlew clean build
	java -jar -Dspring.profiles.active=local build/libs/ews-dues-1.0.0-SNAPSHOT.jar


``` 


## Test it out 

* **Import** ews-dues/json_tests/Dues.postman_collection.json into your POSTMAN
* **Fire up** the Postman Requests for EWS Dues Endpoints 
* **Verify** the Service Response in Postman



