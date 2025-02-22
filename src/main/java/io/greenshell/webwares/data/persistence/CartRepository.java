package io.greenshell.webwares.data.persistence;

import io.greenshell.webwares.data.domain.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByStatus(CartStatus status);
    List<Cart> findByStatusAndCustomer_Id(CartStatus status, Long customerId);
}
