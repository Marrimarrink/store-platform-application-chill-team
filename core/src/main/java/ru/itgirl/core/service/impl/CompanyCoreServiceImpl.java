package ru.itgirl.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itgirl.core.dto.CompanyDto;
import ru.itgirl.core.entity.Company;
import ru.itgirl.core.repository.CompanyRepository;
import ru.itgirl.core.service.CompanyCoreService;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyCoreServiceImpl implements CompanyCoreService {
    private final CompanyRepository companyRepository;

    private CompanyDto convertEntityToDto(Company company) { //stream не используем,тк в полях класса Company нет коллекции
        return CompanyDto.builder()
                .id(company.getId())
                .name_company(company.getName_company())
                .build();
    }
    private Company convertDtoToEntity(CompanyDto companyDto) {
        return Company.builder()
                .name_company(companyDto.getName_company())
                .build();
    }
}
