package ru.itgirl.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itgirl.core.dto.UserDto;
import ru.itgirl.core.entity.User;
import ru.itgirl.core.repository.UserRepository;
import ru.itgirl.core.service.UserCoreService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;



@Service
@RequiredArgsConstructor
@Slf4j
public class UserCoreServiceImpl implements UserCoreService {
    private final UserRepository userRepository;

    @Override
    public UserDto getUserById(Long id) {
        log.info("Try to find user by id {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserDto userDto = convertEntityToDto(user.get());
            log.info("User: {}", userDto.toString());
            return userDto;
        } else {
            log.error("User with id {} not found", id);
            throw new NoSuchElementException("No value present");
        }
    }
  
  @Override
      public List<UserDto> getAllUsers() {
        log.info("Try to find users");
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            log.info("Count found users: {}", users.size());
            return  userRepository.findAll()
                    .stream()
                    .map(this::convertEntityToDto)
                    .toList();
        } else {
            log.error("Users not found");
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public UserDto changeUserRole(Long id) {
        log.info("Try to change user role");

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole("ROLE_MANAGER");
            User changedUser = userRepository.save(user);
            UserDto userDto = convertEntityToDto(changedUser);

            log.info("Changed role for user {}", userDto.toString());
            return userDto;
        } else {
            log.error("Role not changed for User with id {} ", id);
        }
    }

    private UserDto convertEntityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .phone(user.getPhone())
                .isEnabled(user.isEnabled())
                .build();
    }
}
