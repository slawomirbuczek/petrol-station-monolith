package com.pk.petrolstationmonolith.services.user;

import com.pk.petrolstationmonolith.entities.user.*;
import com.pk.petrolstationmonolith.enums.user.UserType;
import com.pk.petrolstationmonolith.models.registration.CompanyRegistrationCredentials;
import com.pk.petrolstationmonolith.models.registration.EmployeeRegistrationCredentials;
import com.pk.petrolstationmonolith.models.registration.IndividualRegistrationCredentials;
import com.pk.petrolstationmonolith.models.user.UserCredentials;
import com.pk.petrolstationmonolith.repositories.*;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public RegistrationService(AddressRepository addressRepository, CompanyRepository companyRepository,
                               EmployeeRepository employeeRepository, IndividualRepository individualRepository, UserRepository userRepository,
                               PasswordEncoder passwordEncoder) {
        this.addressRepository = addressRepository;
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.individualRepository = individualRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        modelMapper = new ModelMapper();
    }


    public UserCredentials registerIndividual(IndividualRegistrationCredentials credentials) {
        Address address = addAddress(modelMapper.map(credentials, Address.class));

        String password = generatePassword();

        User user = modelMapper.map(credentials, User.class);
        user.setUserType(UserType.INDIVIDUAL);
        user.setPassword(passwordEncoder.encode(password));
        user = addUser(user);

        Individual individual = modelMapper.map(credentials, Individual.class);
        individual.setAddress(address);
        individual.setUser(user);
        individualRepository.save(individual);

        return new UserCredentials(user.getUsername(), password);
    }

    public UserCredentials registerCompany(CompanyRegistrationCredentials credentials) {
        Address address = addAddress(modelMapper.map(credentials, Address.class));

        String password = generatePassword();

        User user = modelMapper.map(credentials, User.class);
        user.setUserType(UserType.COMPANY);
        user.setPassword(passwordEncoder.encode(password));
        user = addUser(user);

        Company company = modelMapper.map(credentials, Company.class);
        company.setAddress(address);
        company.setUser(user);
        companyRepository.save(company);

        return new UserCredentials(user.getUsername(), password);
    }

    public UserCredentials registerEmployee(EmployeeRegistrationCredentials credentials) {
        Address address = addAddress(modelMapper.map(credentials, Address.class));

        String password = generatePassword();

        User user = modelMapper.map(credentials, User.class);
        user.setUserType(UserType.EMPLOYEE);
        user.setPassword(passwordEncoder.encode(password));
        user = addUser(user);

        Employee employee = modelMapper.map(credentials, Employee.class);
        employee.setAddress(address);
        employee.setUser(user);
        employeeRepository.save(employee);

        return new UserCredentials(user.getUsername(), password);
    }

    private User addUser(User user) {
        return userRepository.save(user);
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
