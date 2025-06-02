package ru.itgirl.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.web.dto.UserDto;
import ru.itgirl.web.feign.CoreServiceFeignClient;

@Validated
@RestController
//@RequiredArgsConstructor
public class UserWebController {
    private final CoreServiceFeignClient coreServiceFeignClient;

    public UserWebController(CoreServiceFeignClient coreServiceFeignClient) {
        this.coreServiceFeignClient = coreServiceFeignClient;
    }

/*    @GetMapping("/web/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = coreServiceFeignClient.getUserById(id);
        return ResponseEntity.ok(user);
    }*/

 /*   @GetMapping(value = "/web/user/{id}", produces = "application/json")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = coreServiceFeignClient.getUserById(id);
        return ResponseEntity.ok(user);
    }*/

/*    @GetMapping(value = "/web/user/{id}", produces = "application/json")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = coreServiceFeignClient.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }*/

    @GetMapping(value = "/web/user/{id}", produces = "application/json")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = coreServiceFeignClient.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
