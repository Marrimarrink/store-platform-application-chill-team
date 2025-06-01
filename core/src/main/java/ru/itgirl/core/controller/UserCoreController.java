package ru.itgirl.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.UserDto;
import ru.itgirl.core.service.UserCoreService;

@RestController
@RequiredArgsConstructor
public class UserCoreController {
    private final UserCoreService userCoreService;

    @GetMapping("/user/{id}")
    UserDto getUserById(@PathVariable("id") Long id) {
        return userCoreService.getUserById(id);
    }

    @DeleteMapping("/user/{id}")
    void deleteUserById(@PathVariable("id") Long id){
        userCoreService.deleteUser(id);
    }
}
