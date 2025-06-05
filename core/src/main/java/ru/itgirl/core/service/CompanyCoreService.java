package ru.itgirl.core.service;

import ru.itgirl.core.dto.CompanyDto;
import ru.itgirl.core.dto.CompanyCreateDto;
import java.util.List;

public interface CompanyCoreService {
  CompanyDto addCompany(CompanyCreateDto companyCreateDto);

  void deleteCompany(Long id);

    List<CompanyDto> getAllCompanies();
}