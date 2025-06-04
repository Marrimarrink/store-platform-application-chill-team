package ru.itgirl.core.service;

import ru.itgirl.core.dto.UserDto;

public interface UserCoreService {

    UserDto getUserById(Long id);

    UserDto changeUserRole(Long id);
}
