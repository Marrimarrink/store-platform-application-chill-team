package ru.itgirl.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itgirl.core.dto.UserDto;

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
}

