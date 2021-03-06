package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.EmployeeDto;
import com.pk.petrolstationmonolith.entities.account.*;
import com.pk.petrolstationmonolith.entities.emailtoken.EmailToken;
import com.pk.petrolstationmonolith.enums.Roles;
import com.pk.petrolstationmonolith.models.account.UserCredentials;
import com.pk.petrolstationmonolith.models.account.registration.CompanyRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.EmployeeRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.IndividualRegistrationCredentials;
import com.pk.petrolstationmonolith.services.emailtoken.EmailTokenService;
import com.pk.petrolstationmonolith.services.mail.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {

    private final UserService userService;
    private final AddressService addressService;
    private final CompanyService companyService;
    private final IndividualService individualService;
    private final EmployeeService employeeService;
    private final EmailTokenService emailTokenService;
    private final MailService mailService;
    private final ModelMapper mapper;

    public UserCredentials registerIndividual(IndividualRegistrationCredentials credentials) {
        log.trace("Registering new individual");
        String password = generatePassword();

        Customers customers = mapper.map(credentials, Customers.class);
        customers.setRole(Roles.USER_INDIVIDUAL);
        customers.setPassword(password);
        customers = userService.addUser(customers);

        Addresses addresses = mapper.map(credentials, Addresses.class);
        addresses.setCustomers(customers);
        addressService.addAddress(addresses);

        Individuals individuals = mapper.map(credentials, Individuals.class);
        individuals.setCustomers(customers);
        individualService.addIndividual(individuals);

        sendEmailConfimrationMail(customers);
        return new UserCredentials(customers.getUsername(), password);
    }

    public UserCredentials registerCompany(CompanyRegistrationCredentials credentials) {
        log.trace("Registering new company");
        String password = generatePassword();

        Customers customers = mapper.map(credentials, Customers.class);
        customers.setRole(Roles.USER_COMPANY);
        customers.setPassword(password);
        customers = userService.addUser(customers);

        Addresses addresses = mapper.map(credentials, Addresses.class);
        addresses.setCustomers(customers);
        addressService.addAddress(addresses);

        Companies companies = mapper.map(credentials, Companies.class);
        companies.setCustomers(customers);
        companyService.addCompany(companies);

        sendEmailConfimrationMail(customers);
        return new UserCredentials(customers.getUsername(), password);
    }

    public EmployeeDto registerEmployee(EmployeeRegistrationCredentials credentials) {
        log.trace("Registering new employee");
        Customers customers = userService.changeUserRole(credentials.getUserId(), credentials.getRole());

        Employees employees = mapper.map(credentials, Employees.class);
        employees.setCustomers(customers);
        employeeService.addEmployee(employees);

        return employeeService.getEmployeeDto(credentials.getUserId());
    }

    public void confirmEmail(String token) {
        log.trace("Confirming email with token " + token);
        EmailToken emailToken = emailTokenService.getByToken(token);
        userService.activeAccount(emailToken.getCustomers().getId());
        emailTokenService.deleteToken(token);
    }

    private void sendEmailConfimrationMail(Customers customers) {
        EmailToken emailToken = emailTokenService.createNewToken(customers);
        mailService.sendEmailConfirmationMail(customers.getEmail(), emailToken.getToken());
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
