package ru.itgirl.core.service;

import org.springframework.http.ResponseEntity;
import ru.itgirl.core.dto.RegistrationRequest;

import java.util.UUID;

public interface RegistrationCoreService {
    ResponseEntity<String> register(RegistrationRequest request);
}
