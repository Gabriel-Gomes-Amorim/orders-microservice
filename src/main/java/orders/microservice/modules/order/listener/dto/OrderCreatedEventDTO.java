package orders.microservice.modules.order.listener.dto;

import java.util.List;

public record OrderCreatedEventDTO(
    String codeOrder,
    String codeClient,
    List<OrderItemEventDTO> items) {}