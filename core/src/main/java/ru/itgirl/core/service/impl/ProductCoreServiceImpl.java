package ru.itgirl.core.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itgirl.core.dto.CompanyDto;
import ru.itgirl.core.dto.ProductDto;
import ru.itgirl.core.dto.ProductCreateDto;
import ru.itgirl.core.dto.ProductUpdateDto;
import ru.itgirl.core.entity.Company;
import ru.itgirl.core.entity.Product;
import ru.itgirl.core.repository.CompanyRepository;
import ru.itgirl.core.repository.ProductRepository;
import ru.itgirl.core.service.ProductCoreService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCoreServiceImpl implements ProductCoreService {
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

    @Override
    public ProductDto addProduct(ProductCreateDto productCreateDto) {
        log.info("Creating a new product: {}", productCreateDto);

        Company company = companyRepository.findById(productCreateDto.getCompany_id())
                .orElseThrow(() -> {
                    log.error("Company with ID {} not found", productCreateDto.getCompany_id());
                    return new EntityNotFoundException("Company not found");
                });

        Product product = Product.builder()
                .name_product(productCreateDto.getName_product())
                .company(company)
                .build();

        log.debug("Saving new product entity: {}", product);
        Product saved = productRepository.save(product);
        log.info("Product created with ID: {}", saved.getId());

        return convertEntityToDto(saved);
    }

    @Override
    public ProductDto getProductById(Long id) {
        log.info("Fetching product by ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product with ID {} not found", id);
                    return new RuntimeException("Product with ID " + id + " not found");
                });
        log.debug("Product found: {}", product);
        return convertEntityToDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        log.info("Fetching all products");
        List<Product> products = productRepository.findAll();
        log.info("Found {} products", products.size());
        return products.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(ProductUpdateDto productUpdateDto) {
        log.info("Received update request for product ID: {}", productUpdateDto.getId());
        Product product = productRepository.findById(productUpdateDto.getId())
                .orElseThrow(() -> {
                    log.error("Product with ID {} not found", productUpdateDto.getId());
                    return new NoSuchElementException("Product not found");
                });
        log.debug("Found product: {}", product);
        product.setName_product(productUpdateDto.getName_product());
        Company company = companyRepository.findById(productUpdateDto.getCompany_id())
                .orElseThrow(() -> {
                    log.error("Company with ID {} not found", productUpdateDto.getCompany_id());
                    return new NoSuchElementException("Company not found");
                });
        product.setCompany(company);
        log.debug("Updated product entity with new data: name_product={}, company={}",
                product.getName_product(), product.getCompany());
        Product savedProduct = productRepository.save(product);
        log.info("Product with ID {} updated successfully", savedProduct.getId());
        ProductDto productDto = convertEntityToDto(savedProduct);
        log.debug("Converted updated entity to DTO: {}", productDto);
        return productDto;
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Product with ID {} deletion initiated", id);
        if (!productRepository.existsById(id)) {
            log.warn("Product with ID {} does not exist", id);
            throw new NoSuchElementException("Product with ID " + id + " not found");
        }
        productRepository.deleteById(id);
        log.info("Product with ID {} has been deleted", id);
    }

    private ProductDto convertEntityToDto(Product product) {
        if (product == null) {
            log.warn("Attempted to convert null product entity to DTO");
            return null;
        }
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
            log.error("Company ID is required for product creation");
            throw new IllegalArgumentException("Company ID must be provided");
        }

        Company company = companyRepository.findById(productCreateDto.getCompany_id())
                .orElseThrow(() -> {
                    log.error("Company with ID {} not found during product creation", productCreateDto.getCompany_id());
                    return new EntityNotFoundException("Company with ID " + productCreateDto.getCompany_id() + " not found");
                });

        return Product.builder()
                .name_product(productCreateDto.getName_product())
                .company(company)
                .build();
    }
}