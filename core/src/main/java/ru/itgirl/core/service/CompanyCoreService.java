package ru.itgirl.core.service;

import ru.itgirl.core.dto.CompanyCreateDto;
import ru.itgirl.core.dto.CompanyDto;

public interface CompanyCoreService {

    CompanyDto addCompany(CompanyCreateDto companyCreateDto);
}
