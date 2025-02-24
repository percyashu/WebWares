package io.greenshell.webwares.commons.domain;

public record OrderItemDto (Long id, Long quantity, Long productId, Long orderId) {
}