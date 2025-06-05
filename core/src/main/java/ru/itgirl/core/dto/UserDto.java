package ru.itgirl.core.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class UserDto {
    private Long id;
    private String email;
    private String role;
    private String phone;
    private boolean isEnabled;
}
