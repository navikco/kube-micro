Kube Admin
===================================

[![Build Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWSADMINCD)](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWSADMINCD)
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)


- Admin is a Spring Boot Admin Console that provides visibility into running Spring Boot Applications
- It offers ability to look into Registered Applications Health, LIVE LOGs, Resources, Environmental Properties etc.
- It is Spring Boot Application uses Gradle and Rest API Doc
- It uses EUREKA Registry to discover the Running Spring Boot Applications (EWS Microservices)
- It also uses NETFLIX ZUUL to provide standard PROXY via common PORT (8761) to Individual Microservices Ports (Dynamically assigned)
- It packages as Executable JAR


## EWS Admin Resources


| **Useful URLs**	|
| ------------- |
| [Nexus Repo](http://es-nexus01.dal.securustech.net/content/repositories/releases/net/securustech/ews/ews-admin/ "Official Nexus Artifactory for EWS Admin")      	|
| [Git Repo](http://es-bitbucket01.dal.securustech.net/projects/MID/repos/ews-admin/browse "Official Git Repo for EWS Admin")      	|
| [EWS Admin Console - LOCAL](http://localhost:8761/admin/ "EWS Admin Console - LOCAL Environment [Works only when its running LOCALLY]")      	|
| [EWS Admin Console - FREERIDE](http://ld-midsrvcs02.lab.securustech.net:8761/admin/ "EWS Admin Console - FREERIDE Environment")      	|
| [EWS Admin Console - DEV](http://ewsdev.lab.securustech.net:8761/admin/ "EWS Admin Console - DEV Environment")      	|
| [EWS Admin Console - QA](http://ewsqa.lab.securustech.net:8761/admin/ "EWS Admin Console - QA Environment")      	|
| [EWS Admin Console - UAT](http://ua-midsrvc01.pp.securustech.net:8761/admin/ "EWS Admin Console - UAT Environment")      	|


## EWS Admin CI/CD


| Job            | Type (Auto/Manual)	| Status  |Description  |
| ------------- |:-------------:| -----:|-----:|
| [**EWS Admin CI**](http://es-compile01.dal.securustech.net/browse/EP-EWSADMINCD "Admin CI Job")      | Auto | ![Build Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWSADMINCD)	| It triggers automatically when the Code changes are merged to the DEVELOP Branch.	|
| [**EWS Admin DEV Release**](http://es-compile01.dal.securustech.net/browse/EP-EWSDR "EWS Admin Deploy to DEV")      | Manual | ![Deploy Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWSDR)	| It needs to be manually triggered once TeamForge Artifacts are Tagged and Release Notes URL is Verified. **release_version, next_version and other individual Apps being released need to be set** in Bamboo Plan Configuration prior to running this Job. |
| [**EWS Admin RUNWAY**](http://es-compile01.dal.securustech.net/browse/EP-EWLR "EWS Admin Deploy to NEXUS Job")      | Manual | ![Deploy Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EWLR)	| This Job is to upload Build, Package and Update the Release Deployables to NEXUS for QA, PP or PROD Deployments. It needs to be manually triggered once TeamForge Artifacts are Tagged and Release Notes URL is Verified. **release_version, next_version and other individual Apps being released need to be set** in Bamboo Plan Configuration prior to running this Job. |
| [**EWS Admin TAKEOFF**](http://es-compile01.dal.securustech.net/browse/EP-EW "EWS Admin Install to QA Environment")      | Manual | ![Deploy Status](http://es-compile01.dal.securustech.net/plugins/servlet/wittified/build-status/EP-EW)	| This Job is to download desired Release Deployables from NEXUS to the QA Servers and Install them. It needs to be manually triggered once TeamForge Artifacts are Tagged and Release Notes URL is Verified. **release_version and other individual Apps being released need to be set** in Bamboo Plan Configuration prior to running this Job. |

## Available Profiles to run EWS Admin Apps


| EWS Admin         Profile        | Embedded/Externalized           | Description           |
| ------------- |:-------------:|:-------------:|
| **local**      | Embedded In the Code    | Runs in Local Mode with **NO INTRANET** Dependencies |
| **ews_dev**      | Embedded In the Code    | Runs in ews_dev Mode **with DEV INTRANET Environment** Dependencies |
| **freeride**      | Embedded In the Code    | Runs in freeride Mode **with DEV INTRANET Environment** Dependencies |
| **dev**      | Externalized on the Server    | Runs in dev Mode **with DEV INTRANET Environment** Dependencies |
| **qa**      | Externalized on the Server    | Runs in qa Mode **with QA INTRANET Environment** Dependencies |
| **uat**      | Externalized on the Server    | Runs in uat Mode **with UAT INTRANET Environment** Dependencies |


## Build And Run EWS Admin App

```shell

##Build
	cd ews-admin/
	./gradlew clean build

##Run in LOCAL Profile
	java -jar -Dspring.profiles.active=local build/libs/ews-admin-1.0.0-SNAPSHOT.jar

##Run in EWS_DEV Profile
	java -jar -Dspring.profiles.active=ews_dev build/libs/ews-admin-1.0.0-SNAPSHOT.jar

##Run in FREERIDE Profile
    java -Xms512m -Xmx1024m -Dspring.config.location=file:/opt/mw/app/ews/ews-admin/config/ews-admin-freeride.yml,file:/opt/mw/app/ews/ews-admin/custom-config/ews-admin-freeride.yml,file:/opt/mw/app/ews/ews-core/config/freeride/ews-core-es-freeride.yml,file:/opt/mw/app/ews/ews-core/config/freeride/ews-core-ext-freeride.yml,file:/opt/mw/app/ews/ews-core/config/freeride/ews-core-kafka-freeride.yml,file:/opt/mw/app/ews/ews-core/config/freeride/ews-core-mongo-freeride.yml,file:/opt/mw/app/ews/ews-core/config/freeride/ews-core-mysql-freeride.yml,file:/opt/mw/app/ews/ews-core/config/freeride/ews-core-oracle-freeride.yml -Dspring.profiles.active=freeride -jar ews-admin-1.0.6-SNAPSHOT_43.jar

##Run in DEV Profile
    java -Xms512m -Xmx1024m -Dspring.config.location=file:/opt/mw/app/ews/ews-admin/config/ews-admin-dev.yml,file:/opt/mw/app/ews/ews-admin/custom-config/ews-admin-dev.yml,file:/opt/mw/app/ews/ews-core/config/dev/ews-core-es-dev.yml,file:/opt/mw/app/ews/ews-core/config/dev/ews-core-ext-dev.yml,file:/opt/mw/app/ews/ews-core/config/dev/ews-core-kafka-dev.yml,file:/opt/mw/app/ews/ews-core/config/dev/ews-core-mongo-dev.yml,file:/opt/mw/app/ews/ews-core/config/dev/ews-core-mysql-dev.yml,file:/opt/mw/app/ews/ews-core/config/dev/ews-core-oracle-dev.yml -Dspring.profiles.active=dev -jar ews-admin-1.0.6-SNAPSHOT_43.jar

##Run in QA Profile
    java -Xms512m -Xmx1024m -Dspring.config.location=file:/opt/mw/app/ews/ews-admin/config/ews-admin-qa.yml,file:/opt/mw/app/ews/ews-admin/custom-config/ews-admin-qa.yml,file:/opt/mw/app/ews/ews-core/config/qa/ews-core-es-qa.yml,file:/opt/mw/app/ews/ews-core/config/qa/ews-core-ext-qa.yml,file:/opt/mw/app/ews/ews-core/config/qa/ews-core-kafka-qa.yml,file:/opt/mw/app/ews/ews-core/config/qa/ews-core-mongo-qa.yml,file:/opt/mw/app/ews/ews-core/config/qa/ews-core-mysql-qa.yml,file:/opt/mw/app/ews/ews-core/config/qa/ews-core-oracle-qa.yml -Dspring.profiles.active=qa -jar ews-admin-1.0.6-SNAPSHOT_43.jar


``` 


## Test it out 

* **Browse** the SBA (Spring Boot ADMIN) Console with the URL mentioned in 'EWS Admin Resources" section above
* **Verify** the MicroServices (Including ADMIN itself) is registered on SBA Console and you can TAIL LOG and Look at Details of the Microservice(s) registered



