package io.greenshell.webwares.data.domain;

public record OrderItemDto (Long id, Long quantity, Long productId, Long orderId) {
}