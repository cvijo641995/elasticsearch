# Parking Application

## Introduction

For this application, a Park controller created allows to do the following operations with Elasticsearch:

- Load parkings fro csv file to Elasticsearch 
- Find nearest parking from given coordinates
- How many parkings we have in 1km radius

For implementing this project I was using Elasticsearch database, SpringBoot framework, IntelliJ IDEA and Java 11.

## How to run

The first thing to do is to start Elasticsearch. For that you can use the `docker-compose` file in this project
and run it like this:

```bash
$ docker-compose -f docker-compose up -d
``` 

It brings Elasticsearch up.

Then you can run the application like below:

```bash
$ ./mvnw spring-boot:run
```

If your Elasticsearch URI is not `localhost` and/or the cluster name is different simply override one or both of the following environment variable:

- `ES_URI`
- `ES_CLUSTER_NAME`

Once everything is up and running open the browser and go to [http://localhost:8080](http://localhost:8080). You should see Swagger to interact with.

Application can be used from postman with following requests:

- Load parkings fro csv file to Elasticsearch:
  curl --location --request POST 'localhost:8080/v1/parks/load'

- Find nearest parking from given coordinates:
  curl --location --request GET 'localhost:8080/v1/parks/nearest?lat=34.3234&lon=-118.3642'

- How many parkings we have in 1km radius from given coordinates:
  curl --location --request GET 'localhost:8080/v1/parks/radius?lat=33.8304815&lon=-118.1586061'

If you want to check for loaded records you can run:
  curl --location --request GET 'localhost:9200/parks/_search?pretty'

If you want to delete index from elasticsearch:
  curl --location --request DELETE 'localhost:9200/parks?pretty'
