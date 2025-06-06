package ru.itgirl.core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    private Long id;
    @Size(min = 2, max = 50)
    @NotBlank(message = "You must specify the name of the product")
    private String name_product;
    @NotEmpty(message = "You must specify the company")
    private Long company_id;
}
