package ru.itgirl.core.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductUpdateDto {
    @NotNull(message = "Product ID must be provided")
    @Min(value = 1, message = "Product ID must be greater than 0")
    private Long id;

    @NotBlank(message = "Product name must not be blank")
    @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters")
    private String name_product;

    @NotNull(message = "Company ID must be provided")
    @Min(value = 1, message = "Company ID must be greater than 0")
    private Long company_id;
}
