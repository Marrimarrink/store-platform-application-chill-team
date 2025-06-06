package ru.itgirl.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.AuthRequest;
import ru.itgirl.core.dto.AuthResponse;
import ru.itgirl.web.feign.UserCoreServiceClient;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthWebController {
    private final UserCoreServiceClient userCoreServiceClient;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        AuthResponse response = userCoreServiceClient.login(authRequest);
        return ResponseEntity.ok(response);
    }
}
