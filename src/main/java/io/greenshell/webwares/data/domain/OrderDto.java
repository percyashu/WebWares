package io.greenshell.webwares.data.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;

public record OrderDto(Long id,
                       BigDecimal totalPrice,
                       String status,
                       ZonedDateTime shipped,
                       PaymentDto payment,
                       AddressDto shipmentAddress,
                       Set<OrderItemDto> orderItems) {
}