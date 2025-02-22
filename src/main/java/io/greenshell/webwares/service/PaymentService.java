package io.greenshell.webwares.service;

import io.greenshell.webwares.data.domain.PaymentDto;
import io.greenshell.webwares.data.domain.PaymentStatus;
import io.greenshell.webwares.data.persistence.Order;
import io.greenshell.webwares.data.persistence.OrderRepository;
import io.greenshell.webwares.data.persistence.Payment;
import io.greenshell.webwares.data.persistence.PaymentRepository;
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
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public List<PaymentDto> findAll() {
        log.debug("Request to get all Payments");
        return this.paymentRepository.findAll()
                .stream()
                .map(PaymentService::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PaymentDto findById(Long id) {
        log.debug("Request to get Payment : {}", id);

        return this.paymentRepository.findById(id).map(PaymentService::mapToDto).orElse(null);
    }

    public PaymentDto create(PaymentDto paymentDto) {
        log.debug("Request to create Payment : {}", paymentDto);
        Order order =
                this.orderRepository.findById(paymentDto.orderId())
                        .orElseThrow(
                                () -> new IllegalStateException("The Order does not exist!")
                        );
        return mapToDto(this.paymentRepository.save(
                new Payment(
                        paymentDto.paypalPaymentId(),
                        PaymentStatus.valueOf(paymentDto.status()),
                        order
                )
        ));
    }

    public void delete(Long id) {
        log.debug("Request to delete Payment : {}", id);
        this.paymentRepository.deleteById(id);
    }

    public static PaymentDto mapToDto(Payment payment) {
        if (payment != null) {
            return new PaymentDto(
                    payment.getId(),
                    payment.getPaypalPaymentId(),
                    payment.getStatus().name(),
                    payment.getOrder().getId()
            );
        }
        return null;
    }
}