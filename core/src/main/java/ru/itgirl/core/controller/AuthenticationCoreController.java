package ru.itgirl.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.AuthenticationRequest;
import ru.itgirl.core.dto.AuthenticationResponse;
import ru.itgirl.core.entity.User;
import ru.itgirl.core.service.AuthenticationCoreService;
import ru.itgirl.core.util.JWTUtilGenerator;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationCoreController {
    private final AuthenticationCoreService authenticationCoreService;
    private final JWTUtilGenerator jwtUtil;
    @PostMapping("/v1/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authRequest) {
        try {
            User user = authenticationCoreService.authenticate(authRequest.getEmail(), authRequest.getPassword());
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(AuthenticationResponse.builder().token(token).build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("X-Auth-Error", e.getMessage()).build();
        }
    }
}
