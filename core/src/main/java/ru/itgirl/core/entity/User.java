package ru.itgirl.core.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "role", nullable = false, length = 50)
    private String role = "ROLE_USER";

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "enable", nullable = false)
    private boolean isEnabled = false;

    public boolean isEnabled() {
        return isEnabled;
    }
}
