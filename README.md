# Spring Kafka micrometer

Example project to play around with Spring Kafka Micrometer.

Versions used in this project:

* Spring Boot: 2.1.0
* Spring Kafka: 2.2.0.RELEASE
* Micrometer: 1.0.1

## How to build the project

```
./mvnw clean package
```

## How to run this project

Make sure to set the environment variable `DOCKER_HOST_IP` to the ip of the host running the 
Docker containers.

On Linux you can get the correct IP from docker0 interface by executing: ifconfig
In my case `DOCKER_HOST_IP=172.17.0.1`

```
docker-compose up -d
```

Start the producer:

```
cd spring-kafka-producer
mvn spring-boot:run
``` 

The producer will publish a Hello World message to topic: hello-world-messages

Start the consumer:

```
cd
mvn spring-boot:run
``` 

How the consumer metrics:

[http://localhost:8081/metrics/](http://localhost:8081/metrics/)

Kafka metrics are visible.

See specific Kafka metrics:

[http://localhost:8081/actuator/metrics/kafka.consumer.records.consumed.total](http://localhost:8081/actuator/metrics/kafka.consumer.records.consumed.total)

```json
{
  name: "kafka.consumer.records.consumed.total",
  description: "The total number of records consumed.",
  baseUnit: "records",
  measurements: [
    {
      statistic: "COUNT",
      value: "NaN"
    }
  ],
  availableTags: [
    {
      tag: "client.id",
      values: [
        "spring-kafka-consumer-hello-world-app"
      ]
    }
  ]
}
```

My Question why is the value of `COUNT` `NaN`?

```json
{
  measurements: [
    {
      statistic: "COUNT",
      value: "NaN"
    }
  ]
}
```


## Kafka manager 

Part of the `docker-compose` setup is the Kafka Manager.
To get more insight in the broker, topics and more you can use this tool.

* Open [http://localhost:9000](http://localhost:9000)
* Create a Kafka cluster view [http://localhost:9000/addCluster](http://localhost:9000/addCluster)

![](/documentation/images)



