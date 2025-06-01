package ru.itgirl.core.dto;

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
    private Long id;
    @Size(min = 2, max = 50)
    @NotBlank(message = "Необходимо указать наименование товара")
    private String name;
}