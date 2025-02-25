package io.greenshell.webwares.order.service;

import io.greenshell.webwares.commons.domain.OrderItemDto;
import io.greenshell.webwares.order.data.Order;
import io.greenshell.webwares.order.data.OrderItem;
import io.greenshell.webwares.order.repository.OrderItemRepository;
import io.greenshell.webwares.order.repository.OrderRepository;
import io.greenshell.webwares.product.data.Product;
import io.greenshell.webwares.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    public List<OrderItemDto> findAll() {
        log.debug("Request to get all OrderItems");
        return this.orderItemRepository.findAll()
                .stream()
                .map(OrderItemService::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderItemDto findById(Long id) {
        log.debug("Request to get OrderItem : {}", id);
        return this.orderItemRepository.findById(id).map(OrderItemService::mapToDto).orElse(null);
    }

    public OrderItemDto create(OrderItemDto orderItemDto) {
        log.debug("Request to create OrderItem : {}", orderItemDto);
        Order order =
                this.orderRepository.findById(orderItemDto.orderId())
                        .orElseThrow(
                                () ->
                                        new IllegalStateException("The Order does not exist!")
                        );

        return mapToDto(
                this.orderItemRepository.save(
                        new OrderItem(
                                orderItemDto.quantity(),
                                orderItemDto.productId(),
                                order
                        )));
    }

    public void delete(Long id) {
        log.debug("Request to delete OrderItem : {}", id);
        this.orderItemRepository.deleteById(id);
    }

    public static OrderItemDto mapToDto(OrderItem orderItem) {
        if (orderItem != null) {
            return new OrderItemDto(
                    orderItem.getId(),
                    orderItem.getQuantity(),
                    orderItem.getProductId(),
                    orderItem.getOrder().getId()
            );
        }
        return null;
    }
}