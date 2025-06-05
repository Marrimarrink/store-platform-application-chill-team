package ru.itgirl.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.RegistrationRequest;
import ru.itgirl.core.repository.UserRepository;
import ru.itgirl.core.service.impl.EmailCoreServiceImpl;
import ru.itgirl.core.service.impl.RegistrationCoreServiceImpl;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationCoreController {

    private final RegistrationCoreServiceImpl registrationCoreService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        return registrationCoreService.register(request);
    }

}
