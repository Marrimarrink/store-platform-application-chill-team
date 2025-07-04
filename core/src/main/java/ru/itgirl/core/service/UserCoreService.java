package ru.itgirl.core.service;

import ru.itgirl.core.dto.UserDto;
import ru.itgirl.core.entity.User;

import java.util.List;

public interface UserCoreService {

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();
  
   UserDto changeUserRole(Long id);

    void deleteUser(Long id);

}
