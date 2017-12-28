# Recruiting Service

Recruiting service handles complete recruitment cycle starting from job opening till offers.

### Tech stack

* Spring Boot
* Spring Boot Test/JUnit/Mockito
* Slf4j
* Open source libraries(Lombok/Commons-collection/Commons-io)

### APIs

#### Offer

* Create

** Endpoint - /api/v1/offer
** Method - POST
** Payload - {
               "tle": "SE-1",
               "s_dt": "2017-12-18"
             }
             
* Get

** Endpoint - /api/v1/offer/{offerId}
** Method - GET

* Get All

** Endpoint - /api/v1/offer
** Method - GET

* Get offer applications

** Endpoint - /api/v1/offer/{offerId}/applications
** Method - GET

#### Application

* Create

** Endpoint - /api/v1/application
** Method - POST
** Payload - {
               "c_el": "test@test.com",
               "res": "Resume",
               "o_id": "{offerId}"
             }
             
* Get

** Endpoint - /api/v1/application/{applicationId}
** Method - GET

* Update Application status

** Endpoint - /api/v1/application
** Method - PUT
** Payload - {
               "id": "{applicationId}",
                "sts": "{APPLIED, INVITED, REJECTED, HIRED}"
             }

### Configuration

* Configuration can be either added in application.properties or overwritten with command line args.

### Test

* ToDo - Smoke test are not included

### Build and Run


Run `./service.sh` to see usage. For a starter:

```
./service.sh           : prints usage
./service.sh build : builds your app
./service.sh run   : runs your app
```

If you have `docker` installed on your machine, you can also run all the
scripts and tests inside a docker container:

```
./service.sh docker_build : packages your app into a docker image
./service.sh docker_run   : runs your app using a docker image


### Improvement(Not able to complete because of time constraint)

* More logging
* Mock based test
* Smoke test
