package io.greenshell.webwares.commons.domain;

public record CartDto(Long id, Long orderId, CustomerDto customerDto, String status) {
}