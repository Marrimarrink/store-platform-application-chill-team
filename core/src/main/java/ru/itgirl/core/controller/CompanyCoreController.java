package ru.itgirl.core.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.itgirl.core.dto.CompanyCreateDto;
import ru.itgirl.core.dto.CompanyDto;
import ru.itgirl.core.service.CompanyCoreService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CompanyCoreController {
    private final CompanyCoreService companyCoreService;

    @PostMapping("/v1/companies")
    CompanyDto addCompany(@RequestBody @Valid CompanyCreateDto companyCreateDto) {
        return companyCoreService.addCompany(companyCreateDto);
    }
  
      @DeleteMapping("/v1/companies/{id}")
    void deleteCompany(@PathVariable("id") Long id) {
        companyCoreService.deleteCompany(id);
    }
}

