package io.greenshell.webwares.data.domain;

public record CartDto(Long id, Long orderId, CustomerDto customerDto, String status) {
}