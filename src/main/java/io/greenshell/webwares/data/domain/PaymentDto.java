package io.greenshell.webwares.data.domain;

public record PaymentDto(Long id, String paypalPaymentId, String status, Long orderId) {
}