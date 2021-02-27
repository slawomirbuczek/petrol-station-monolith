package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.CompanyDto;
import com.pk.petrolstationmonolith.entities.account.Company;
import com.pk.petrolstationmonolith.exceptions.account.company.CompanyNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper mapper;

    public CompanyService(CompanyRepository companyRepository, ModelMapper mapper) {
        this.companyRepository = companyRepository;
        this.mapper = mapper;
    }

    public CompanyDto getCompanyDto(long userId) {
        return mapCompanyToDto(getCompany(userId));
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public CompanyDto updateCompany(long userId, CompanyDto companyDto) {
        Company oldCompany = getCompany(userId);

        Company company = mapDtoToCompany(companyDto);
        company.setId(oldCompany.getId());
        company.setUser(oldCompany.getUser());
        company = companyRepository.save(company);

        return mapCompanyToDto(company);
    }

    public CompanyDto deleteCompany(long userId) {
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
