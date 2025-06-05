package ru.itgirl.core.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itgirl.core.dto.CompanyDto;
import ru.itgirl.core.dto.ProductDto;
import ru.itgirl.core.dto.ProductCreateDto;
import ru.itgirl.core.entity.Company;
import ru.itgirl.core.entity.Product;
import ru.itgirl.core.repository.CompanyRepository;
import ru.itgirl.core.repository.ProductRepository;
import ru.itgirl.core.service.ProductCoreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCoreServiceImpl implements ProductCoreService {
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

//    @Override
//    public ProductDto getProductByName(String name_product) {
//        log.info("Получение товара по названию: {}", name_product);
//        Product product = productRepository.findByName_product(name_product).orElseThrow();
//        return convertEntityToDto(product);
//    }

    @Override
    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        log.info("Создание нового товара: {}", productCreateDto);

        Company company = companyRepository.findById(productCreateDto.getCompany_id())
                .orElseThrow(() -> new EntityNotFoundException("Компания не найдена"));

        Product product = Product.builder()
                .name_product(productCreateDto.getName_product())
                .company(company)
                .build();

        Product saved = productRepository.save(product);

        return convertEntityToDto(saved);
    }

    @Override
    public ProductDto getProductById(Long id) {
        log.info("Получение товара по ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Товар с ID " + id + " не найден"));
        return convertEntityToDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Удаление товара с ID: {}", id);
        productRepository.deleteById(id);
        log.info("Товар с ID {} - удалён", id);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        log.info("Получение всех товаров");
        List<Product> products = productRepository.findAll();
        log.info("Найдено {} товаров", products.size());
        return products.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private ProductDto convertEntityToDto(Product product) {
        if (product == null) return null;

        return ProductDto.builder()
                .id(product.getId())
                .name_product(product.getName_product())
                .company(CompanyDto.builder()
                        .id(product.getCompany().getId())
                        .name_company(product.getCompany().getName_company())
                        .build())
                .build();
    }

    private Product convertDtoToEntity(ProductCreateDto productCreateDto) {
        if (productCreateDto.getCompany_id() == null) {
            throw new IllegalArgumentException("Необходимо указать ID компании");
        }

        Company company = companyRepository.findById(productCreateDto.getCompany_id())
                .orElseThrow(() -> new EntityNotFoundException("Компания с ID " + productCreateDto.getCompany_id() + " не найдена"));

        return Product.builder()
                .name_product(productCreateDto.getName_product())
                .company(company)
                .build();
    }
}

