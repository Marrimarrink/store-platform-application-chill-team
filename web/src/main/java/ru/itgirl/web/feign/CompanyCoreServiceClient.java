package ru.itgirl.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itgirl.core.dto.CompanyCreateDto;
import ru.itgirl.core.dto.CompanyDto;

@FeignClient(
        name = "CompanyCore",
        url = "http://localhost:8081"
)
public interface CompanyCoreServiceClient {

    @PostMapping("/api/v1/companies")
    CompanyDto addCompany(@RequestBody /*@Valid*/ CompanyCreateDto companyCreateDto);
}