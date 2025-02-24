package io.greenshell.webwares.product.repository;

import io.greenshell.webwares.product.data.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}