package io.greenshell.webwares.order.data;

import io.greenshell.webwares.commons.persistence.BaseEntity;
import io.greenshell.webwares.product.data.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "order_item")
public class OrderItem extends BaseEntity {

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    private Long productId;

    @ManyToOne
    private Order order;
}