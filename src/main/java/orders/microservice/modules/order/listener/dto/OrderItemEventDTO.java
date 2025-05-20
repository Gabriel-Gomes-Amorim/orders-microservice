package orders.microservice.modules.order.listener.dto;

import java.math.BigDecimal;

public record OrderItemEventDTO(
    String product,
    Integer quantity,
    BigDecimal price ) {}