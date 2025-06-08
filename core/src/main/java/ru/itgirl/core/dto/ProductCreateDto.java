package ru.itgirl.core.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductCreateDto {
    @Size(min = 2, max = 50)
    @NotBlank(message = "You must specify the name of the product")
    private String name_product;
    @Min(value = 1, message = "You must specify the company")
    private Long company_id;
}