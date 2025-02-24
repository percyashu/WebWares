package io.greenshell.webwares.commons.domain;

public record PaymentDto(Long id, String paypalPaymentId, String status, Long orderId) {
}