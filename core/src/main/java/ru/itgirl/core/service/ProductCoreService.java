package ru.itgirl.core.service;

import ru.itgirl.core.dto.ProductCreateDto;
import ru.itgirl.core.dto.ProductDto;

import java.util.List;

public interface ProductCoreService {
    //ProductDto getProductByName(String name_product);

    ProductDto getProductById(Long id);

    ProductDto createProduct(ProductCreateDto productCreateDto);

    void deleteProduct(Long id);

    List<ProductDto> getAllProducts();
}
