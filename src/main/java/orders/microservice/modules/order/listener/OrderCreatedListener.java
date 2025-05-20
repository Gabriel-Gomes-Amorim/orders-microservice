package orders.microservice.modules.order.listener;

import static orders.microservice.config.RabbitMQConfig.ORDER_CREATED_QUEUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import orders.microservice.modules.order.listener.dto.OrderCreatedEventDTO;
import orders.microservice.modules.order.services.SaveOrderService;
@Component
public class OrderCreatedListener {
    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class); 

    @Autowired
    private SaveOrderService saveOrderService;
    
    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEventDTO> message){
        logger.info("Message consumed: {}", message);

        saveOrderService.execute(message.getPayload());
    }
}
