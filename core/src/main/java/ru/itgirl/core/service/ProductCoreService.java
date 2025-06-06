package ru.itgirl.core.service;

import ru.itgirl.core.dto.ProductCreateDto;
import ru.itgirl.core.dto.ProductDto;
import ru.itgirl.core.dto.ProductUpdateDto;

import java.util.List;

public interface ProductCoreService {
    ProductDto addProduct(ProductCreateDto productCreateDto);

    ProductDto getProductById(Long id);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(ProductUpdateDto productUpdateDto);

    void deleteProduct(Long id);
}
