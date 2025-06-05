package ru.itgirl.core.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
    private Long id;
    private String name_product;
    private CompanyDto company;
}

