package ru.itgirl.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itgirl.web.dto.UserDto;

@FeignClient(
        name = "Core",
        url = "http://localhost:8083"
)
public interface CoreServiceFeignClient {

    @GetMapping("/user/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
}

