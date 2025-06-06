package ru.itgirl.core.service;

import ru.itgirl.core.dto.UserDto;

import java.util.List;

public interface UserCoreService {

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();
}
