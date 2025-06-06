package ru.itgirl.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.RegistrationRequestCore;
import ru.itgirl.core.dto.RegistrationResponse;
import ru.itgirl.web.dto.RegistrationRequest;
import ru.itgirl.web.feign.UserCoreServiceClient;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class RegistrationWebController {
    private final UserCoreServiceClient userCoreServiceClientClient;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Пароли не совпадают");
        }
        RegistrationRequestCore coreRequest = convertToCore(request);
        RegistrationResponse response = userCoreServiceClientClient.registerUser(coreRequest);
        if ("error".equals(response.getStatus())) {
            return ResponseEntity.badRequest().body(response.getMessage());
        } else {
            return ResponseEntity.ok(response.getMessage());
        }
    }

    private RegistrationRequestCore convertToCore(RegistrationRequest request) {
        String phone = (request.getPhone() != null && !request.getPhone().isEmpty()) ? request.getPhone() : null;
        return new RegistrationRequestCore(request.getEmail(), request.getPassword(), phone);
    }
}
