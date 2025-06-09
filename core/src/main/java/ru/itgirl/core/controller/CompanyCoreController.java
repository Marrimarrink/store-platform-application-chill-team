package ru.itgirl.core.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.itgirl.core.dto.CompanyCreateDto;
import ru.itgirl.core.dto.CompanyDto;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itgirl.core.service.CompanyCoreService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CompanyCoreController {
    private final CompanyCoreService companyCoreService;

    @PostMapping("/v1/companies")
    public ResponseEntity<Map<String, Object>> addCompany(@RequestBody @Valid CompanyCreateDto companyCreateDto) {
        CompanyDto companyDto = companyCoreService.addCompany(companyCreateDto);
        Map<String, Object> response = new HashMap<>();
        response.put("id", companyDto.getId());
        response.put("name_company", companyDto.getName_company());
        response.put("message", "Company added successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/v1/companies/{id}")
    public ResponseEntity<Map<String, String>> deleteCompany(@PathVariable("id") Long id) {
        companyCoreService.deleteCompany(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Company deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v1/companies")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companies = companyCoreService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }
}

