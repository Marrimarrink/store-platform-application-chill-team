package ru.itgirl.web.feign;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.core.dto.ProductCreateDto;
import ru.itgirl.core.dto.ProductDto;
import ru.itgirl.core.dto.ProductUpdateDto;

import java.util.List;

@FeignClient(
        name = "ProductCore",
        url = "http://localhost:8080"
)
public interface ProductCoreServiceClient {
    @PostMapping("/api/v1/products")
    ProductDto addProduct(@RequestBody @Valid ProductCreateDto productCreateDto);

    @GetMapping("/api/v1/products/{id}")
    ProductDto getProductById(@PathVariable("id") Long id);

    @GetMapping("/api/v1/products")
    List<ProductDto> getAllProducts();

    @PutMapping("/api/v1/products")
    ProductDto updateProduct(@RequestBody @Valid ProductUpdateDto productUpdateDto);

    @DeleteMapping("/api/v1/products/{id}")
    void deleteProduct(@PathVariable("id") Long id);
}
