package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.CompanyDto;
import com.pk.petrolstationmonolith.entities.account.Companies;
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

    public void addCompany(Companies companies) {
        log.trace("Adding new company for user with id " + companies.getCustomers().getId());
        companyRepository.save(companies);
    }

    public CompanyDto updateCompany(long userId, CompanyDto companyDto) {
        log.trace("Updating company for user with id " + userId);
        Companies oldCompanies = getCompany(userId);

        Companies companies = mapDtoToCompany(companyDto);
        companies.setId(oldCompanies.getId());
        companies.setCustomers(oldCompanies.getCustomers());
        companies = companyRepository.save(companies);

        return mapCompanyToDto(companies);
    }

    public CompanyDto deleteCompany(long userId) {
        log.trace("Deleting company for user with id " + userId);
        Companies companies = getCompany(userId);
        companyRepository.delete(companies);
        return mapCompanyToDto(companies);
    }

    private Companies getCompany(long userId) {
        return companyRepository.findByUserId(userId)
                .orElseThrow(() -> new CompanyNotFoundException(userId));
    }

    private CompanyDto mapCompanyToDto(Companies companies) {
        return mapper.map(companies, CompanyDto.class);
    }

    private Companies mapDtoToCompany(CompanyDto companyDto) {
        return mapper.map(companyDto, Companies.class);
    }

}
