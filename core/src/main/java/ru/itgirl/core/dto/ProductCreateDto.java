package ru.itgirl.core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductCreateDto {
    @Size(min = 2, max = 50)
    @NotBlank(message = "Необходимо указать наименование товара")
    private String name_product;

    @NotEmpty(message = "Необходимо указать хотя бы одну компанию")
    private Long company_id;
}