package ru.itgirl.core.service;

import org.springframework.http.ResponseEntity;
import ru.itgirl.core.dto.ActivationResponse;
import ru.itgirl.core.dto.RegistrationRequestCore;
import ru.itgirl.core.dto.RegistrationResponse;

public interface RegistrationCoreService {
    ResponseEntity<RegistrationResponse> register(RegistrationRequestCore request);

    ResponseEntity<ActivationResponse> activate(String activationKey);

}
