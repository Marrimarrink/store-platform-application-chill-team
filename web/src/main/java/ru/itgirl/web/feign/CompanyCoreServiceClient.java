package ru.itgirl.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "CompanyCore",
        url = "http://localhost:8080"
)
public interface CompanyCoreServiceClient {
    @DeleteMapping("/api/v1/companies/{id}")
    void deleteCompany(@PathVariable("id") Long id);
}