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

    public CompanyDto getCompanyDto(long customerId) {
        log.trace("Getting company dto for customer with id " + customerId);
        return mapCompanyToDto(getCompany(customerId));
    }

    public void addCompany(Companies companies) {
        log.trace("Adding new company for customer with id " + companies.getCustomers().getId());
        companyRepository.save(companies);
    }

    public CompanyDto updateCompany(long customerId, CompanyDto companyDto) {
        log.trace("Updating company for customer with id " + customerId);
        Companies oldCompanies = getCompany(customerId);

        Companies companies = mapDtoToCompany(companyDto);
        companies.setId(oldCompanies.getId());
        companies.setCustomers(oldCompanies.getCustomers());
        companies = companyRepository.save(companies);

        return mapCompanyToDto(companies);
    }

    public CompanyDto deleteCompany(long customerId) {
        log.trace("Deleting company for customer with id " + customerId);
        Companies companies = getCompany(customerId);
        companyRepository.delete(companies);
        return mapCompanyToDto(companies);
    }

    private Companies getCompany(long customerId) {
        return companyRepository.findByCustomersId(customerId)
                .orElseThrow(() -> new CompanyNotFoundException(customerId));
    }

    private CompanyDto mapCompanyToDto(Companies companies) {
        return mapper.map(companies, CompanyDto.class);
    }

    private Companies mapDtoToCompany(CompanyDto companyDto) {
        return mapper.map(companyDto, Companies.class);
    }

}
