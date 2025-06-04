package ru.itgirl.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.core.dto.UserDto;
import ru.itgirl.core.service.UserCoreService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserCoreController {
    private final UserCoreService userCoreService;

    @GetMapping("/v1/users/{id}")
    UserDto getUserById(@PathVariable("id") Long id) {
        return userCoreService.getUserById(id);
    }

    @PostMapping("/v1/users/changes/{id}")
    UserDto changeUserRole(@PathVariable("id") Long id) {
        return userCoreService.changeUserRole(id);
    }
}
