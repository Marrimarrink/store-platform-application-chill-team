package ru.itgirl.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.core.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
