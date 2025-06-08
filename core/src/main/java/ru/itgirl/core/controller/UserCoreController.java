package ru.itgirl.core.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.UserDto;
import ru.itgirl.core.entity.User;
import ru.itgirl.core.service.UserCoreService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserCoreController {
    private final UserCoreService userCoreService;

    @GetMapping("/v1/users/{id}")
    UserDto getUserById(@PathVariable("id") Long id) {
        return userCoreService.getUserById(id);
    }

     @GetMapping("/v1/users")
   List<UserDto> getAllUsers() {
        return userCoreService.getAllUsers();
    }

    @PostMapping("/v1/users/changes/{id}")
    UserDto changeUserRole(@PathVariable("id") Long id) {
        return userCoreService.changeUserRole(id);
    }

    @DeleteMapping("v1/users/{id}")
    void deleteUser(@PathVariable("id") Long id) {
        userCoreService.deleteUser(id);
    }
}
