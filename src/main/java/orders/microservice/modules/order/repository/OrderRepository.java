package orders.microservice.modules.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import orders.microservice.modules.order.entity.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {}
