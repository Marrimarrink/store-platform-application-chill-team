package ru.itgirl.core.service;

import ru.itgirl.core.dto.ProductDto;

public interface ProductCoreService {
    ProductDto getProductByName(String name);

    ProductDto getProductById(Long id);
}
