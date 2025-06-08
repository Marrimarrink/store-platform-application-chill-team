package ru.itgirl.core.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.core.dto.ProductCreateDto;
import ru.itgirl.core.dto.ProductDto;
import ru.itgirl.core.dto.ProductUpdateDto;
import ru.itgirl.core.service.ProductCoreService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/v1/products")
    public ResponseEntity<Map<String, Object>> updateProduct(@RequestBody @Valid ProductUpdateDto productUpdateDto) {
        ProductDto updatedProduct = productCoreService.updateProduct(productUpdateDto);
        Map<String, Object> response = new HashMap<>();
        response.put("id", updatedProduct.getId());
        response.put("name_product", updatedProduct.getName_product());
        response.put("message", "Product card updated successfully");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/v1/products/{id}")
    void deleteProduct(@PathVariable("id") Long id) {
        productCoreService.deleteProduct(id);
    }
}