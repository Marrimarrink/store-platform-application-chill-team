package ru.itgirl.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.AuthRequest;
import ru.itgirl.core.dto.AuthResponse;
import ru.itgirl.core.entity.User;
import ru.itgirl.core.service.JWTUtil;
import ru.itgirl.core.service.UserCoreService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserCoreService userCoreService;
    private final JWTUtil jwtUtil;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        User user = userCoreService.authenticate(authRequest.getEmail(), authRequest.getPassword());
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
