package ru.itgirl.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.UserDto;
import ru.itgirl.web.feign.UserCoreServiceClient;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class UserWebController {
    private final UserCoreServiceClient userCoreServiceClient;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userCoreServiceClient.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userCoreServiceClient.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
