package ru.itgirl.core.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.core.dto.ProductCreateDto;
import ru.itgirl.core.dto.ProductDto;
import ru.itgirl.core.service.ProductCoreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductCoreController {
    private final ProductCoreService productCoreService;

    @GetMapping("/product/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productCoreService.getProductById(id);
    }

    @PostMapping("/product/create")
    ProductDto createProduct(@RequestBody @Valid ProductCreateDto productCreateDto) {
        return productCoreService.createProduct(productCreateDto);
    }

    @DeleteMapping("/product/delete/{id}")
    void deleteProduct(@PathVariable("id") Long id) {
        productCoreService.deleteProduct(id);
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productCoreService.getAllProducts();
    }
}
