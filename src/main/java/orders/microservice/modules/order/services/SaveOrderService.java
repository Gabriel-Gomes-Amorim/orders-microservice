package orders.microservice.modules.order.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orders.microservice.modules.order.entity.OrderEntity;
import orders.microservice.modules.order.entity.OrderItem;
import orders.microservice.modules.order.listener.dto.OrderCreatedEventDTO;
import orders.microservice.modules.order.repository.OrderRepository;

@Service
public class SaveOrderService {
    @Autowired
    private OrderRepository orderRepository;

  public void execute(OrderCreatedEventDTO event) {

        var entity = new OrderEntity();

        entity.setOrderId(event.codeOrder());
        entity.setCustomerId(event.codeClient());
        entity.setItems(getOrderItems(event));
        entity.setTotal(getTotal(event));

        orderRepository.save(entity);
    }

    private BigDecimal getTotal(OrderCreatedEventDTO event) {
        return event.items()
                .stream()
                .map(i -> i.price().multiply(BigDecimal.valueOf(i.quantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static List<OrderItem> getOrderItems(OrderCreatedEventDTO event) {
        return event.items().stream()
                .map(i -> new OrderItem(i.product(), i.quantity(), i.price()))
                .toList();
    }
}
