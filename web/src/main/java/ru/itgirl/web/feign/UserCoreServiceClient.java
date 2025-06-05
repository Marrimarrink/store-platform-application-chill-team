package ru.itgirl.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itgirl.core.dto.RegistrationRequestCore;
import ru.itgirl.core.dto.RegistrationResponse;
import ru.itgirl.core.dto.UserDto;
import ru.itgirl.web.dto.RegistrationRequest;

@FeignClient(
        name = "Core",
        url = "http://localhost:8081"
)
public interface UserCoreServiceClient {

    @GetMapping("/api/v1/users/{id}")
    UserDto getUserById(@PathVariable("id") Long id);

    @PostMapping("/api/auth/register")
    RegistrationResponse registerUser(@RequestBody RegistrationRequestCore registrationRequestCore);
}

