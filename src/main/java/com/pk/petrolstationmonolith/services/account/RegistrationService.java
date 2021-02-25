package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.entities.account.*;
import com.pk.petrolstationmonolith.enums.account.UserType;
import com.pk.petrolstationmonolith.models.account.UserCredentials;
import com.pk.petrolstationmonolith.models.account.registration.CompanyRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.EmployeeRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.IndividualRegistrationCredentials;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class RegistrationService {

    private final UserService userService;
    private final AddressService addressService;
    private final CompanyService companyService;
    private final IndividualService individualService;
    private final EmployeeService employeeService;
    private final ModelMapper mapper;

    public RegistrationService(UserService userService, AddressService addressService, CompanyService companyService,
                               IndividualService individualService, EmployeeService employeeService) {
        this.userService = userService;
        this.addressService = addressService;
        this.companyService = companyService;
        this.individualService = individualService;
        this.employeeService = employeeService;
        mapper = new ModelMapper();
    }

    public UserCredentials registerIndividual(IndividualRegistrationCredentials credentials) {
        String password = generatePassword();

        User user = mapper.map(credentials, User.class);
        user.setUserType(UserType.INDIVIDUAL);
        user.setPassword(password);
        user = userService.addUser(user);

        Address address = mapper.map(credentials, Address.class);
        address = addressService.addAddress(address);

        Individual individual = mapper.map(credentials, Individual.class);
        individual.setAddress(address);
        individual.setUser(user);
        individualService.addIndividual(individual);

        return new UserCredentials(user.getUsername(), password);
    }

    public UserCredentials registerCompany(CompanyRegistrationCredentials credentials) {
        String password = generatePassword();

        User user = mapper.map(credentials, User.class);
        user.setUserType(UserType.COMPANY);
        user.setPassword(password);
        user = userService.addUser(user);

        Address address = mapper.map(credentials, Address.class);
        address = addressService.addAddress(address);

        Company company = mapper.map(credentials, Company.class);
        company.setAddress(address);
        company.setUser(user);
        companyService.addCompany(company);

        return new UserCredentials(user.getUsername(), password);
    }

    public UserCredentials registerEmployee(EmployeeRegistrationCredentials credentials) {
        String password = generatePassword();

        User user = mapper.map(credentials, User.class);
        user.setUserType(UserType.EMPLOYEE);
        user.setPassword(password);
        user = userService.addUser(user);

        Address address = mapper.map(credentials, Address.class);
        address = addressService.addAddress(address);

        Individual individual = mapper.map(credentials, Individual.class);
        individual.setAddress(address);
        individual.setUser(user);
        individual = individualService.addIndividual(individual);

        Employee employee = mapper.map(credentials, Employee.class);
        employee.setAddress(address);
        employee.setUser(user);
        employee.setIndividual(individual);
        employeeService.addEmployee(employee);

        return new UserCredentials(user.getUsername(), password);
    }

    private String generatePassword() {
        char[] allAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789".toCharArray();
        StringBuilder password = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < 9; i++) {
            password.append(allAllowed[random.nextInt(allAllowed.length)]);
        }
        return password.toString();
    }

}
