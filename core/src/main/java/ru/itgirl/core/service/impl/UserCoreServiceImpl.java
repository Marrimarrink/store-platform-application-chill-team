package ru.itgirl.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public User authenticate(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User  not found"));
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        return user;
    }

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
            return userRepository.findAll()
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
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public void deleteUser(Long id) {
        log.info("User with ID {} deletion initiated", id);
        if (!userRepository.existsById(id)) {
            log.warn("User with ID {} does not exist", id);
            throw new NoSuchElementException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
        log.info("User with ID {} has been deleted", id);
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
