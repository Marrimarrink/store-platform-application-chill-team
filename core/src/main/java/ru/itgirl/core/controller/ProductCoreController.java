package ru.itgirl.core.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.core.dto.ProductCreateDto;
import ru.itgirl.core.dto.ProductDto;
import ru.itgirl.core.dto.ProductUpdateDto;
import ru.itgirl.core.service.ProductCoreService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductCoreController {
    private final ProductCoreService productCoreService;

    @PostMapping("/v1/products")
    public ProductDto addProduct(@RequestBody @Valid ProductCreateDto productCreateDto) {
        return productCoreService.addProduct(productCreateDto);
    }

    @GetMapping("/v1/products/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productCoreService.getProductById(id);
    }

    @GetMapping("/v1/products")
    public List<ProductDto> getAllProducts() {
        return productCoreService.getAllProducts();
    }

    @PutMapping("/v1/products/{id}")
    public ProductDto updateProduct(@RequestBody @Valid ProductUpdateDto productUpdateDto) {
        return productCoreService.updateProduct(productUpdateDto);
    }

    @DeleteMapping("/v1/products/{id}")
    void deleteProduct(@PathVariable("id") Long id) {
        productCoreService.deleteProduct(id);
    }
}