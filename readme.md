# Orders Microservice

This microservice manages orders by consuming events from a RabbitMQ queue and saving the orders in a MongoDB database.

## Technologies

- Java 21
- Spring Boot 3.4.5
- Spring Data MongoDB
- Spring AMQP (RabbitMQ)
- Lombok
- MongoDB
- RabbitMQ

## Description

The service listens to "order created" events on a RabbitMQ queue, processes the order data, calculates the total, and stores the `Order` entity in MongoDB.

## Features

- Consumes JSON messages from the RabbitMQ queue (`orders-microservice`)
- Maps incoming messages to the `OrderCreatedEventDTO`
- Saves orders with items and total amount to MongoDB
- Indexes the `customerId` field for faster queries

## Main Components

- `OrderEntity` — Represents the Order with a list of `OrderItem`
- `OrderCreatedListener` — Listens and consumes RabbitMQ messages
- `SaveOrderService` — Handles saving the Order entity to MongoDB
- `RabbitMQConfig` — Configures the queue and JSON message converter for RabbitMQ

## How to Run

### Prerequisites

- Java 21+
- Maven

### Using Docker Compose for Dependencies

```bash
docker-compose up -d
```

### Configuration

spring.application.name=ordersmicroservice

spring.data.mongodb.authentication-database=admin
spring.data.mongodb.auto-index-creation=true
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=ordersmicroservice
spring.data.mongodb.username=orders
spring.data.mongodb.password=ordersmicroservice

### Message Payload Example

The microservice expects messages in the following JSON format when consuming from RabbitMQ:

```bash
{
  "orderCode": 1001,
  "customerCode": 1,
  "items": [
    {
      "product": "pencil",
      "quantity": 100,
      "price": 1.10
    },
    {
      "product": "notebook",
      "quantity": 10,
      "price": 1.00
    }
  ]
}
```
