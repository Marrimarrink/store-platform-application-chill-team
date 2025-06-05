package ru.itgirl.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.dto.CompanyDto;
import ru.itgirl.core.service.CompanyCoreService;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class CompanyCoreController {
    private final CompanyCoreService companyCoreService;

    @GetMapping("/companies")
    public List<CompanyDto> getAllCompanies() {
        return companyCoreService.getAllCompanies();
    }

    @GetMapping("/company/{id}")
    public CompanyDto getCompanyById(@PathVariable long id){
        return companyCoreService.getCompanyById(id);
    }
}
