package ru.itgirl.web.controller;


import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.core.dto.UserDto;
import ru.itgirl.web.feign.UserCoreServiceClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
  
   @PostMapping ("/users/changes/{id}")
    public ResponseEntity<UserDto> changeUserRole(@PathVariable Long id) {
        UserDto user = userCoreServiceClient.changeUserRole(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        userCoreServiceClient.deleteUser(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Deleted successfully");
        return ResponseEntity.ok(response);
    }
}