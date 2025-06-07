package ru.itgirl.core.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.core.dto.ActivationResponse;
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

    @GetMapping("/activate")
    public ResponseEntity<ActivationResponse> activate(@RequestParam("uuid") String uuid) {
        return registrationCoreService.activate(uuid);
    }

}
