package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.CompanyDto;
import com.pk.petrolstationmonolith.entities.account.Company;
import com.pk.petrolstationmonolith.exceptions.account.company.CompanyNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper mapper;

    public CompanyDto getCompanyDto(long userId) {
        log.trace("Getting company dto for user with id " + userId);
        return mapCompanyToDto(getCompany(userId));
    }

    public void addCompany(Company company) {
        log.trace("Adding new company for user with id " + company.getUser().getId());
        companyRepository.save(company);
    }

    public CompanyDto updateCompany(long userId, CompanyDto companyDto) {
        log.trace("Updating company for user with id " + userId);
        Company oldCompany = getCompany(userId);

        Company company = mapDtoToCompany(companyDto);
        company.setId(oldCompany.getId());
        company.setUser(oldCompany.getUser());
        company = companyRepository.save(company);

        return mapCompanyToDto(company);
    }

    public CompanyDto deleteCompany(long userId) {
        log.trace("Deleting company for user with id " + userId);
        Company company = getCompany(userId);
        companyRepository.delete(company);
        return mapCompanyToDto(company);
    }

    private Company getCompany(long userId) {
        return companyRepository.findByUserId(userId)
                .orElseThrow(() -> new CompanyNotFoundException(userId));
    }

    private CompanyDto mapCompanyToDto(Company company) {
        return mapper.map(company, CompanyDto.class);
    }

    private Company mapDtoToCompany(CompanyDto companyDto) {
        return mapper.map(companyDto, Company.class);
    }

}
