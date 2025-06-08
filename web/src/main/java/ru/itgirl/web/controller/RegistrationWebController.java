package ru.itgirl.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.core.dto.ActivationResponse;
import ru.itgirl.core.dto.RegistrationRequestCore;
import ru.itgirl.core.dto.RegistrationResponse;
import ru.itgirl.core.dto.UserDto;
import ru.itgirl.web.dto.RegistrationRequest;
import ru.itgirl.web.feign.UserCoreServiceClient;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class RegistrationWebController {
    private final UserCoreServiceClient userCoreServiceClientClient;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.badRequest()
                    .body(new RegistrationResponse("error","passwords do not match, try again"));
        }
        RegistrationRequestCore coreRequest = convertToCore(request);
        RegistrationResponse response = userCoreServiceClientClient.registerUser(coreRequest);
        if ("error".equals(response.getStatus())) {
            return ResponseEntity.badRequest()
                    .body(new RegistrationResponse(response.getStatus(), response.getMessage()));
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }

    private RegistrationRequestCore convertToCore(RegistrationRequest request) {
        String phone = (request.getPhone() != null && !request.getPhone().isEmpty()) ? request.getPhone() : null;
        return new RegistrationRequestCore(request.getEmail(), request.getPassword(), phone);
    }

    @GetMapping("/activate")
    public ResponseEntity<ActivationResponse> activate(@RequestParam("uuid") String uuid) {
        ActivationResponse response = userCoreServiceClientClient.activate(uuid);
        return ResponseEntity.ok(response);
    }
}
