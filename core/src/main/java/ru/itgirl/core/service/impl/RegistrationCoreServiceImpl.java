package ru.itgirl.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.itgirl.core.dto.RegistrationRequest;
import ru.itgirl.core.repository.UserRepository;
import ru.itgirl.core.service.RegistrationCoreService;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class RegistrationCoreServiceImpl implements RegistrationCoreService {
    private final EmailCoreServiceImpl emailCoreService;

    @Override
    public ResponseEntity<String> register(RegistrationRequest request) {
        String uuid = UUID.randomUUID().toString();
        String activationLink = "http://localhost:8081/api/auth/activate?uuid=" + uuid;
        emailCoreService.sendActivationEmail(request.getEmail(), activationLink);
        return ResponseEntity.ok("Регистрация успешна! Проверьте почту для активации.");
    }
}
