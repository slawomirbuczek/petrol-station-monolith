package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.EmployeeDto;
import com.pk.petrolstationmonolith.entities.account.*;
import com.pk.petrolstationmonolith.enums.account.UserType;
import com.pk.petrolstationmonolith.models.account.UserCredentials;
import com.pk.petrolstationmonolith.models.account.registration.CompanyRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.EmployeeRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.IndividualRegistrationCredentials;
import com.pk.petrolstationmonolith.services.emailtoken.EmailTokenService;
import com.pk.petrolstationmonolith.services.mail.MailService;
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
    private final EmailTokenService emailTokenService;
    private final MailService mailService;
    private final ModelMapper mapper;

    public RegistrationService(UserService userService, AddressService addressService, CompanyService companyService,
                               IndividualService individualService, EmployeeService employeeService, EmailTokenService emailTokenService, MailService mailService, ModelMapper mapper) {
        this.userService = userService;
        this.addressService = addressService;
        this.companyService = companyService;
        this.individualService = individualService;
        this.employeeService = employeeService;
        this.emailTokenService = emailTokenService;
        this.mailService = mailService;
        this.mapper = mapper;
    }

    public UserCredentials registerIndividual(IndividualRegistrationCredentials credentials) {
        String password = generatePassword();

        User user = mapper.map(credentials, User.class);
        user.setUserType(UserType.INDIVIDUAL);
        user.setPassword(password);
        user = userService.addUser(user);

        Address address = mapper.map(credentials, Address.class);
        address.setUser(user);
        addressService.addAddress(address);

        Individual individual = mapper.map(credentials, Individual.class);
        individual.setUser(user);
        individualService.addIndividual(individual);

        sendEmailConfimrationMail(user);
        return new UserCredentials(user.getUsername(), password);
    }

    public UserCredentials registerCompany(CompanyRegistrationCredentials credentials) {
        String password = generatePassword();

        User user = mapper.map(credentials, User.class);
        user.setUserType(UserType.COMPANY);
        user.setPassword(password);
        user = userService.addUser(user);

        Address address = mapper.map(credentials, Address.class);
        address.setUser(user);
        addressService.addAddress(address);

        Company company = mapper.map(credentials, Company.class);
        company.setUser(user);
        companyService.addCompany(company);

        sendEmailConfimrationMail(user);
        return new UserCredentials(user.getUsername(), password);
    }

    public EmployeeDto registerEmployee(EmployeeRegistrationCredentials credentials) {
        User user = userService.changeUserTypeToEmployeeAndSetRole(credentials.getUserId(), credentials.getRole());

        Employee employee = mapper.map(credentials, Employee.class);
        employee.setUser(user);
        employeeService.addEmployee(employee);

        return employeeService.getEmployeeDto(credentials.getUserId());
    }

    public void confirmEmail(String token) {
        EmailToken emailToken = emailTokenService.getByToken(token);
        userService.activeAccount(emailToken.getUser().getId());
        emailTokenService.deleteToken(token);
    }

    private void sendEmailConfimrationMail(User user) {
        EmailToken emailToken = emailTokenService.createNewToken(user);
        mailService.sendEmailConfirmationMail(user.getEmail(), emailToken.getToken());
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
