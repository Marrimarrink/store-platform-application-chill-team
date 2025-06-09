package ru.itgirl.web.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "library-users")
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

    @PutMapping("/products")
    public ResponseEntity<Map<String, Object>> updateProduct(@RequestBody ProductUpdateDto productUpdateDto) {
        ProductDto updatedProduct = productCoreServiceClient.updateProduct(productUpdateDto);
        Map<String, Object> response = new HashMap<>();
        response.put("id", updatedProduct.getId());
        response.put("name_product", updatedProduct.getName_product());
        response.put("message", "Product card updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        productCoreServiceClient.deleteProduct(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Deleted successfully");
        return ResponseEntity.ok(response);
    }
}
