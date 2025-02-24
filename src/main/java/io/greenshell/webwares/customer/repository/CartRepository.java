package io.greenshell.webwares.customer.repository;

import io.greenshell.webwares.commons.domain.CartStatus;
import io.greenshell.webwares.customer.data.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByStatus(CartStatus status);
    List<Cart> findByStatusAndCustomer_Id(CartStatus status, Long customerId);
}
