package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.CustomerDto;
import com.pk.petrolstationmonolith.entities.account.Customers;
import com.pk.petrolstationmonolith.entities.emailtoken.EmailToken;
import com.pk.petrolstationmonolith.enums.Roles;
import com.pk.petrolstationmonolith.exceptions.account.EmailAlreadyTakenException;
import com.pk.petrolstationmonolith.exceptions.account.InvalidEmailTokenException;
import com.pk.petrolstationmonolith.exceptions.account.customer.CustomerNotFoundException;
import com.pk.petrolstationmonolith.models.account.RequestUpdateEmail;
import com.pk.petrolstationmonolith.repositories.account.CustomerRepository;
import com.pk.petrolstationmonolith.services.emailtoken.EmailTokenService;
import com.pk.petrolstationmonolith.services.mail.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EmailTokenService emailTokenService;
    private final MailService mailService;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    public Customers getCustomer(long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public Customers getCustomer(String customerId) {
        try {
            return getCustomer(Long.parseLong(customerId));
        } catch (Exception e) {
            throw new CustomerNotFoundException(customerId);
        }
    }

    public Customers getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(email));
    }

    public CustomerDto getCustomerDto(long customerId) {
        log.trace("Getting customer dto for customer with id " + customerId);
        return mapCustomerToDto(getCustomer(customerId));
    }

    public Customers addCustomer(Customers customers) {
        log.trace("Adding new customer with email " + customers.getEmail() + "and role " + customers.getRole());
        customers.setPassword(encodePassword(customers.getPassword()));
        if (customerRepository.existsByEmail(customers.getEmail())) {
            throw new EmailAlreadyTakenException(customers.getEmail());
        }
        return customerRepository.save(customers);
    }

    public void updatePassword(Customers customers, String newPassword) {
        customers.setPassword(encodePassword(newPassword));
        customerRepository.save(customers);
    }

    public boolean passwordMatches(String encodedPassword, String password) {
        return encoder.matches(password, encodedPassword);
    }

    public Customers changeCustomerRole(long customerId, Roles role) {
        log.trace("Changing customer with id " + customerId + " role to " + role);
        Customers customers = getCustomer(customerId);
        customers.setRole(role);
        return customerRepository.save(customers);
    }

    public void activeAccount(long customerId) {
        log.trace("Activiating account for customer with id " + customerId);
        Customers customers = getCustomer(customerId);
        customers.setEnabled(true);
        customerRepository.save(customers);
    }

    public void sendUpdateEmailMail(long customerId) {
        log.trace("Sending update email to customer with id " + customerId);
        Customers customers = getCustomer(customerId);
        EmailToken emailToken = emailTokenService.createNewToken(customers);
        mailService.sendEmailUpdateMail(customers.getEmail(), emailToken.getToken());
    }

    public void updateEmail(long customerId, RequestUpdateEmail request) {
        log.trace("Updating email for customer with id " + customerId);
        if (emailTokenService.tokenNotValid(request.getToken(), customerId)) {
            throw new InvalidEmailTokenException();
        }

        Customers customers = getCustomer(customerId);
        customers.setEmail(request.getEmail());
        customerRepository.save(customers);
        emailTokenService.deleteToken(request.getToken());
    }

    public CustomerDto disableCustomer(long customerId) {
        log.trace("Disabling customer with id " + customerId);
        Customers customers = getCustomer(customerId);
        customers.setEnabled(false);
        customers = customerRepository.save(customers);
        return mapCustomerToDto(customers);
    }

    public String getOwnerEmail() {
        return customerRepository.findFirstByRole(Roles.OWNER).getEmail();
    }

    private String encodePassword(String password) {
        return encoder.encode(password);
    }

    private CustomerDto mapCustomerToDto(Customers customers) {
        return mapper.map(customers, CustomerDto.class);
    }

}
