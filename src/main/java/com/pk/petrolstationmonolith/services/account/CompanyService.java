package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.CompanyDto;
import com.pk.petrolstationmonolith.entities.account.Company;
import com.pk.petrolstationmonolith.exceptions.account.CompanyNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyByUserId(long userId) {
        return companyRepository.findByUserId(userId).orElseThrow(() -> new CompanyNotFoundException(userId));
    }

    public CompanyDto mapCompanyToDto(Company company) {
        return new ModelMapper().map(company, CompanyDto.class);
    }

}
