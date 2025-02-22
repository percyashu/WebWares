package io.greenshell.webwares.data.domain;

import java.math.BigDecimal;
import java.util.Set;

public record ProductDto(Long id, String name, String description,
                         BigDecimal price, Integer quantity, String status,
                         Integer salesCounter, Set<ReviewDto> reviews, CategoryDto category) {
}