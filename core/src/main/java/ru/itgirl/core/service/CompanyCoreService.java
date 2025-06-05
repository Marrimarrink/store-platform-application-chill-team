package ru.itgirl.core.service;

import ru.itgirl.core.dto.CompanyDto;
import ru.itgirl.core.dto.CompanyCreateDto;
import java.util.List;

public interface CompanyCoreService {
    List<CompanyDto> getAllCompanies();

    CompanyDto getCompanyById(Long id);

  CompanyDto addCompany(CompanyCreateDto companyCreateDto);

  void deleteCompany(Long id);
}