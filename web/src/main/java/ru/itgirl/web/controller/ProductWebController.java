package ru.itgirl.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.core.dto.ProductCreateDto;
import ru.itgirl.core.dto.ProductDto;
import ru.itgirl.core.dto.ProductUpdateDto;
import ru.itgirl.web.feign.ProductCoreServiceClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequiredArgsConstructor
public class ProductWebController {
    private final ProductCoreServiceClient productCoreServiceClient;

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductCreateDto productCreateDto) {
        ProductDto createdProduct = productCoreServiceClient.addProduct(productCreateDto);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto productDto = productCoreServiceClient.getProductById(id);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> allProducts= productCoreServiceClient.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDto productUpdateDto) {
        ProductDto updatedProduct = productCoreServiceClient.updateProduct(id, productUpdateDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        productCoreServiceClient.deleteProduct(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Deleted successfully");
        return ResponseEntity.ok(response);
    }
}
