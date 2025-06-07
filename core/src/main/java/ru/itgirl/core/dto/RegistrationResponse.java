package ru.itgirl.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationResponse {
    private Long id;
    private String phone;
    private String email;
    private String role;
    private boolean isEnabled;
    private String status;
    private String message;

    public RegistrationResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
