package ru.itgirl.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itgirl.core.dto.CompanyCreateDto;
import ru.itgirl.core.dto.CompanyDto;
import ru.itgirl.core.entity.Company;
import ru.itgirl.core.repository.CompanyRepository;
import ru.itgirl.core.service.CompanyCoreService;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyCoreServiceImpl implements CompanyCoreService {
    private final CompanyRepository companyRepository;

   @Override
    public CompanyDto addCompany(CompanyCreateDto companyCreateDto) {
        log.info("Try to create company using input data: {}",companyCreateDto.toString());
        Company company = companyRepository.save(convertDtoToEntity(companyCreateDto));
        CompanyDto companyDto = convertEntityToDto(company);
        log.info("New company created: {}", companyDto.toString());
        return companyDto;
    }

    private CompanyDto convertEntityToDto(Company company) { //stream не используем,тк в полях класса Company нет коллекции
        return CompanyDto.builder()
                .id(company.getId())
                .name_company(company.getName_company())
                .build();
    }
    private Company convertDtoToEntity(CompanyCreateDto companyCreateDto) {
        return Company.builder()
                .name_company(companyCreateDto.getName_company())
                .build();
    }
}
