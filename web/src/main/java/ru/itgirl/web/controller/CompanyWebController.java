package ru.itgirl.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.CompanyCreateDto;
import ru.itgirl.core.dto.CompanyDto;
import ru.itgirl.web.feign.CompanyCoreServiceClient;

@Validated
@RestController
@RequiredArgsConstructor
public class CompanyWebController {
    private final CompanyCoreServiceClient companyCoreServiceClient;

    @PostMapping("/companies")
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyCreateDto companyCreateDto) {
        CompanyDto createdCompany = companyCoreServiceClient.addCompany(companyCreateDto);
        return ResponseEntity.ok(createdCompany);
    }
}
