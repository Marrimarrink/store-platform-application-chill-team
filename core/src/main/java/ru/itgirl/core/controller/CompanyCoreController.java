package ru.itgirl.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.core.service.CompanyCoreService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CompanyCoreController {
    private final CompanyCoreService companyCoreService;

    @DeleteMapping("/v1/companies/{id}")
    void deleteCompany(@PathVariable("id") Long id) {
        companyCoreService.deleteCompany(id);
    }
}