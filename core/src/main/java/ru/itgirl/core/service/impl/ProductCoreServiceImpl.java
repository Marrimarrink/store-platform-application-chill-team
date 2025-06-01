package ru.itgirl.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itgirl.core.dto.ProductDto;
import ru.itgirl.core.dto.ProductCreateDto;
import ru.itgirl.core.entity.Product;
import ru.itgirl.core.repository.ProductRepository;
import ru.itgirl.core.service.ProductCoreService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCoreServiceImpl implements ProductCoreService {
    private final ProductRepository productRepository;

    @Override
    public ProductDto getProductByName(String name) {
        log.info("Получение товара по названию: {}", name);
        Product product = productRepository.findProductByName(name).orElseThrow();
        return convertEntityToDto(product);
    }

    @Override
    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        log.info("Создание нового товара: {}", productCreateDto.getName());
        Product product = productRepository.save(convertDtoToEntity(productCreateDto));
        ProductDto productDto = convertEntityToDto(product);
        log.info("Товар создан с ID: {}", productDto.getId());
        return productDto;
    }

    @Override
    public ProductDto getProductById(Long id) {
        log.info("Получение товара по ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Товар с ID " + id + " не найден"));
        return convertEntityToDto(product);
    }

    private ProductDto convertEntityToDto(Product product) {

        // !!! Снять комментарии после добавления CompanyDto !!!
        //List<CompanyDto> companyDtoList = null;
//        if (product.getCompanies() != null) {
//            companyDtoList = product.getCompanies()
//                    .stream()
//                    .map(company -> CompanyDto.builder()
//                            .name(company.getName())
//                            .id(company.getId())
//                            .build())
//                    .toList();
//        }
        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
 //               .companies(companyDtoList)
                .build();
        return productDto;
    }

    private Product convertDtoToEntity(ProductCreateDto productCreateDto) {
        Product product = new Product();
        product.setName(productCreateDto.getName());
        return product;
    }
}

