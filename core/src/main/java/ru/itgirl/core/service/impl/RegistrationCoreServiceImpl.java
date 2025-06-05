package ru.itgirl.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itgirl.core.dto.RegistrationRequestCore;
import ru.itgirl.core.dto.RegistrationResponse;
import ru.itgirl.core.entity.User;
import ru.itgirl.core.repository.UserRepository;
import ru.itgirl.core.service.RegistrationCoreService;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class RegistrationCoreServiceImpl implements RegistrationCoreService {
    private final EmailCoreServiceImpl emailCoreService;
    private final UserRepository userRepository;
    private final Map<String, String> activationLinks = new ConcurrentHashMap<>();

    @Override
    public ResponseEntity<RegistrationResponse> register(RegistrationRequestCore request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(new RegistrationResponse
                            ("error", "Пользователь с таким email уже зарегистрирован"));
        }
        String passwordHash = new BCryptPasswordEncoder().encode(request.getPassword());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordHash);
        user.setEnabled(false);
        userRepository.save(user);
        String uuid = UUID.randomUUID().toString();
        String activationLink = "http://localhost:8081/auth/activate?uuid=" + uuid;
        activationLinks.put(uuid, request.getEmail());
        emailCoreService.sendActivationEmail(request.getEmail(), activationLink);
        return ResponseEntity.ok(new RegistrationResponse
                ("success", "Регистрация успешна! Проверьте почту для активации."));
    }
}
