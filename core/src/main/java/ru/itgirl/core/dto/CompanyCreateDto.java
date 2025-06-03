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
public class CompanyCreateDto {
    @Size(min = 1, max = 50)
    @NotBlank(message = "It is necessary to specify the name of the brand company.")
    private String name_company;
}
