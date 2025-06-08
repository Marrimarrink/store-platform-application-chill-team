package ru.itgirl.core.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthRequest {
    private String email;
    private String password;
}
