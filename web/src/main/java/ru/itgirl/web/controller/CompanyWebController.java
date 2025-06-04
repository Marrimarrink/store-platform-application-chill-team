package ru.itgirl.web.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itgirl.core.dto.CompanyCreateDto;
import ru.itgirl.core.dto.CompanyDto;

import ru.itgirl.web.feign.CompanyCoreServiceClient;

@Validated
@RestController
@RequiredArgsConstructor
public class CompanyWebController {
    private final CompanyCoreServiceClient companyCoreServiceClient;


    @DeleteMapping("/companies/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyCoreServiceClient.deleteCompany(id);
    }

    @PostMapping("/companies")
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyCreateDto companyCreateDto) {
        CompanyDto createdCompany = companyCoreServiceClient.addCompany(companyCreateDto);
        return ResponseEntity.ok(createdCompany);
    }
}
