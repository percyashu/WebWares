package io.greenshell.webwares.data.domain;

import java.util.Set;

public record CategoryDto (Long id, String name, String description, Set<ProductDto> products) {
}