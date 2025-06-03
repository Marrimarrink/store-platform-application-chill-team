package ru.itgirl.core.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String email;
    private String password;
}
