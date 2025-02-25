package io.greenshell.webwares.order.service;

import io.greenshell.webwares.commons.domain.CartDto;
import io.greenshell.webwares.commons.domain.OrderDto;
import io.greenshell.webwares.commons.domain.OrderStatus;
import io.greenshell.webwares.order.data.Order;
import io.greenshell.webwares.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderDto> findAll() {
        log.debug("Request to get all Orders");
        return this.orderRepository.findAll()
                .stream()
                .map(OrderService::mapToDto)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public OrderDto findById(Long id) {
        log.debug("Request to get Order : {}", id);
        return this.orderRepository.findById(id).map(OrderService::mapToDto).orElse(null);
    }
    public List<OrderDto> findAllByUser(Long id) {
        return this.orderRepository.findByCartId(id)
                .stream()
                .map(OrderService::mapToDto)
                .collect(Collectors.toList());
    }
    public OrderDto create(OrderDto orderDto) {
        log.debug("Request to create Order : {}", orderDto);
        return mapToDto(
                this.orderRepository.save(
                        new Order(
                                BigDecimal.ZERO,
                                OrderStatus.CREATION,
                                null,
                                null,
                                null,
                                Collections.emptySet(),
                                null
                        )
                )
        );
    }
    public Order create(CartDto cartDto) {
        log.debug("Request to create Order with a Cart : {}", cartDto);
        return this.orderRepository.save(
                new Order(
                        BigDecimal.ZERO,
                        OrderStatus.CREATION,
                        null,
                        null,
                        null,
                        Collections.emptySet(),
                        cartDto.id()
                )
        );
    }
    public void delete(Long id) {
        log.debug("Request to delete Order : {}", id);
        this.orderRepository.deleteById(id);
    }
    public static OrderDto mapToDto(Order order) {
        if (order != null) {

            return new OrderDto(
                    order.getId(),
                    order.getTotalPrice(),
                    order.getStatus().name(),
                    order.getShipped(),
                    PaymentService.mapToDto(order.getPayment()),
                    AddressService.mapToDto(order.getShipmentAddress()),
                    order.getOrderItems()
                            .stream()
                            .map(OrderItemService::mapToDto)
                            .collect(Collectors.toSet())
            );
        }
        return null;
    }
}