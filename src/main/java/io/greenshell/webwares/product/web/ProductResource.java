package io.greenshell.webwares.product.web;

import io.greenshell.webwares.commons.domain.ProductDto;
import io.greenshell.webwares.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.greenshell.webwares.commons.util.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/products")
public class ProductResource {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAll() {
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return this.productService.create(productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.productService.delete(id);
    }
}