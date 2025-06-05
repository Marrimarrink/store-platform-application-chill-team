package ru.itgirl.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.RegistrationRequestCore;
import ru.itgirl.core.dto.RegistrationResponse;
import ru.itgirl.core.service.impl.RegistrationCoreServiceImpl;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationCoreController {

    private final RegistrationCoreServiceImpl registrationCoreService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequestCore request) {
        return registrationCoreService.register(request);
    }

}
