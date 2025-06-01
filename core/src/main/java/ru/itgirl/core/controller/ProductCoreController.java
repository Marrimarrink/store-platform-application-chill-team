package ru.itgirl.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.ProductDto;
import ru.itgirl.core.service.ProductCoreService;

@RestController
@RequiredArgsConstructor
public class ProductCoreController {
    private final ProductCoreService productCoreService;

    @GetMapping("/product/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productCoreService.getProductById(id);
    }
}
