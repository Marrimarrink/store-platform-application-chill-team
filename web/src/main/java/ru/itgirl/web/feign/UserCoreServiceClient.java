package ru.itgirl.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.core.dto.RegistrationRequestCore;
import ru.itgirl.core.dto.RegistrationResponse;
import ru.itgirl.core.dto.AuthenticationRequest;
import ru.itgirl.core.dto.AuthenticationResponse;
import ru.itgirl.core.dto.UserDto;
import ru.itgirl.core.dto.*;

import java.util.List;

@FeignClient(
        name = "UserCore",
        url = "http://localhost:8080"
)

public interface UserCoreServiceClient {

    @GetMapping("/api/v1/users/{id}")
    UserDto getUserById(@PathVariable("id") Long id);

    @GetMapping("/api/v1/users")
    List<UserDto> getAllUsers();

    @PostMapping("/api/v1/users/changes/{id}")
    UserDto changeUserRole(@PathVariable("id") Long id);

    @PostMapping("/api/auth/register")
    RegistrationResponse registerUser(@RequestBody RegistrationRequestCore registrationRequestCore);

    @GetMapping("/api/auth/activate")
    ActivationResponse activate(@RequestParam("uuid") String uuid);

    @PostMapping("/api/v1/auth/login")
    AuthenticationResponse login(@RequestBody AuthenticationRequest authRequest);

    @DeleteMapping("/api/v1/users/{id}")
    void deleteUser(@PathVariable("id") Long id);
}
