package ru.itgirl.core.service;

import ru.itgirl.core.dto.CompanyDto;

import java.util.List;

public interface CompanyCoreService {
    List<CompanyDto> getAllCompanies();

    CompanyDto getCompanyById(Long id);
}
