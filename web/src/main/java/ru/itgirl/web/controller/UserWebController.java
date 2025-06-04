package ru.itgirl.web.controller;


import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.UserDto;
import ru.itgirl.web.feign.UserCoreServiceClient;
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

    @PostMapping ("/users/changes/{id}")
    public ResponseEntity<UserDto> changeUserRole(@PathVariable Long id) {
        UserDto user = userCoreServiceClient.changeUserRole(id);
        return ResponseEntity.ok(user);
    }


}
