package ru.itgirl.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @NotBlank(message = "Email обязателен")
    @Email
    private String email;
    @NotBlank(message = "Пароль обязателен")
    @Size(min = 8, message = "Пароль должен быть не менее 8 символов")
    private String password;
    @NotBlank(message = "Подтверждение пароля обязательно")
    private String confirmPassword;
    @Pattern(regexp = "^[0-9+\\-() ]*$", message = "Некорректные символы в телефоне")
    private String phone;
}
