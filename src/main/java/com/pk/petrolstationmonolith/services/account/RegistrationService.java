package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.entities.account.*;
import com.pk.petrolstationmonolith.enums.account.UserType;
import com.pk.petrolstationmonolith.exceptions.account.EmailAlreadyTakenException;
import com.pk.petrolstationmonolith.models.account.registration.CompanyRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.EmployeeRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.IndividualRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.UserCredentials;
import com.pk.petrolstationmonolith.repositories.account.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class RegistrationService {

    private final AddressRepository addressRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final IndividualRepository individualRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public RegistrationService(AddressRepository addressRepository, CompanyRepository companyRepository,
                               EmployeeRepository employeeRepository, IndividualRepository individualRepository,
                               UserService userService, PasswordEncoder passwordEncoder) {
        this.addressRepository = addressRepository;
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.individualRepository = individualRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        modelMapper = new ModelMapper();
    }


    public UserCredentials registerIndividual(IndividualRegistrationCredentials credentials) {
        Address address = addAddress(modelMapper.map(credentials, Address.class));

        User user = modelMapper.map(credentials, User.class);
        user.setUserType(UserType.INDIVIDUAL);

        String password = generatePassword();
        user = addUser(user, password);

        Individual individual = modelMapper.map(credentials, Individual.class);
        individual.setAddress(address);
        individual.setUser(user);
        individualRepository.save(individual);

        return new UserCredentials(user.getUsername(), password);
    }

    public UserCredentials registerCompany(CompanyRegistrationCredentials credentials) {
        Address address = addAddress(modelMapper.map(credentials, Address.class));

        User user = modelMapper.map(credentials, User.class);
        user.setUserType(UserType.COMPANY);

        String password = generatePassword();
        user = addUser(user, password);

        Company company = modelMapper.map(credentials, Company.class);
        company.setAddress(address);
        company.setUser(user);
        companyRepository.save(company);

        return new UserCredentials(user.getUsername(), password);
    }

    public UserCredentials registerEmployee(EmployeeRegistrationCredentials credentials) {
        Address address = addAddress(modelMapper.map(credentials, Address.class));

        User user = modelMapper.map(credentials, User.class);
        user.setUserType(UserType.EMPLOYEE);

        String password = generatePassword();
        user = addUser(user, password);

        Employee employee = modelMapper.map(credentials, Employee.class);
        employee.setAddress(address);
        employee.setUser(user);
        employeeRepository.save(employee);

        return new UserCredentials(user.getUsername(), password);
    }

    private User addUser(User user, String password) {
        if (userService.emailAlreadyTaken(user.getEmail())) {
            throw new EmailAlreadyTakenException(user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(password));
        return userService.saveUser(user);
    }

    private Address addAddress(Address address) {
        return addressRepository.save(address);
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
