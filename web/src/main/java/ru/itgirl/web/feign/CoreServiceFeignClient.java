package ru.itgirl.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Core")
public interface CoreServiceFeignClient {


    //@GetMapping("/user/{id}") // Эндпоинт целевого микросервиса

}

