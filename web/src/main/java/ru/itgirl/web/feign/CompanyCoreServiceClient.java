package ru.itgirl.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itgirl.core.dto.CompanyCreateDto;
import ru.itgirl.core.dto.CompanyDto;

@FeignClient(
        name = "CompanyCore",
        url = "http://localhost:8081"
)
public interface CompanyCoreServiceClient {
    @DeleteMapping("/api/v1/companies/{id}")
    void deleteCompany(@PathVariable("id") Long id);

    @PostMapping("/api/v1/companies")
    CompanyDto addCompany(@RequestBody /*@Valid*/ CompanyCreateDto companyCreateDto);

}