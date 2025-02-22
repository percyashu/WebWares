package io.greenshell.webwares.service;

import io.greenshell.webwares.data.domain.ProductDto;
import io.greenshell.webwares.data.persistence.CategoryRepository;
import io.greenshell.webwares.data.persistence.Product;
import io.greenshell.webwares.data.persistence.ProductRepository;
import io.greenshell.webwares.data.persistence.ProductStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDto> findAll() {
        log.debug("Request to get all Products");
        return this.productRepository.findAll()
                .stream()
                .map(ProductService::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        log.debug("Request to get Product : {}", id);
        return this.productRepository.findById(id).map(ProductService::mapToDto).orElse(null);
    }

    public ProductDto create(ProductDto productDto) {
        log.debug("Request to create Product : {}", productDto);
        return mapToDto(this.productRepository.save(
                new Product(
                        productDto.name(),
                        productDto.description(),
                        productDto.price(),
                        productDto.quantity(),
                        ProductStatus.valueOf(productDto.status()),
                        productDto.salesCounter(),
                        Collections.emptySet(),
                        this.categoryRepository.findById(productDto.category().id()).orElse(null))
        ));
    }

    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        this.productRepository.deleteById(id);
    }

    public static ProductDto mapToDto(Product product) {
        if (product != null) {
            return new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getStatus().name(),
                    product.getSalesCounter(),
                    product.getReviews().stream().map(ReviewService::mapToDto).collect(Collectors.toSet()),
                    CategoryService.mapToDto(product.getCategory())
            );
        }
        return null;
    }
}