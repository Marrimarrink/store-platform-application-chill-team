package ru.itgirl.core.service;

import ru.itgirl.core.dto.UserDto;
import ru.itgirl.core.entity.User;

public interface UserCoreService {

    UserDto getUserById(Long id);
    User authenticate(String email, String rawPassword);
}
