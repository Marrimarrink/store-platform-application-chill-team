package ru.itgirl.core.service;

import ru.itgirl.core.entity.User;

public interface AuthenticationCoreService {
    User authenticate(String email, String rawPassword);
}
