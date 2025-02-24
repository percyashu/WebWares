package io.greenshell.webwares.product.web;

import io.greenshell.webwares.commons.domain.ReviewDto;
import io.greenshell.webwares.product.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.greenshell.webwares.commons.util.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/reviews")
public class ReviewResource {
    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDto> findAll() {
        return this.reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ReviewDto findById(@PathVariable Long id) {
        return this.reviewService.findById(id);
    }

    @PostMapping
    public ReviewDto create(@RequestBody ReviewDto reviewDto) {
        return this.reviewService.create(reviewDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.reviewService.delete(id);
    }
}