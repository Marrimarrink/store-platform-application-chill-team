package ru.itgirl.web.controller;

import feign.FeignException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.AuthenticationRequest;
import ru.itgirl.core.dto.AuthenticationResponse;
import ru.itgirl.web.feign.UserCoreServiceClient;
import ru.itgirl.web.util.JWTUtilVerifier;
import ru.itgirl.web.util.TokenBlacklist;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationWebController {
    private final UserCoreServiceClient userCoreServiceClient;
    private final JWTUtilVerifier jwtUtilVerifier;
    private final TokenBlacklist tokenBlacklist;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authRequest) {
        try {
            AuthenticationResponse response = userCoreServiceClient.login(authRequest);
            return ResponseEntity.ok(response);
        } catch (FeignException e) {
            return ResponseEntity.status(e.status()).body(e.responseHeaders().get("X-Auth-Error"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Claims claims = jwtUtilVerifier.validateToken(token);
            if (claims != null) {
                long expirationTime = claims.getExpiration().getTime();
                tokenBlacklist.addToken(token, expirationTime);
                return ResponseEntity.ok().body("{\"message\": \"User sign out\"}");
            } else {
                return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body("{\"error\": \"Invalid token\"}");
            }
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body("{\"error\": \"No token provided\"}");
        }
    }
}