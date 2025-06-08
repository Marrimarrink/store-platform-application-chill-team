package ru.itgirl.web.controller;

import feign.FeignException;
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

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationWebController {
    private final UserCoreServiceClient userCoreServiceClient;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authRequest) {
        try {
            AuthenticationResponse response = userCoreServiceClient.login(authRequest);
            return ResponseEntity.ok(response);
        } catch (FeignException e) {
            return ResponseEntity.status(e.status()).body(e.responseHeaders().get("X-Auth-Error"));
        }
    }
}
