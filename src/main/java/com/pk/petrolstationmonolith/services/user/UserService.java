package com.pk.petrolstationmonolith.services.user;

import com.pk.petrolstationmonolith.enums.user.Roles;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.user.RequestUpdatePassword;
import com.pk.petrolstationmonolith.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final AddressRepository addressRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final IndividualRepository individualRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserService(AddressRepository addressRepository, CompanyRepository companyRepository,
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

    public ResponseMessage updatePassword(RequestUpdatePassword request, String username) {
        if (!username.equals(getUsername()) && !hasRole(Roles.ADMIN)) {
            return new ResponseMessage();
        }



        return new ResponseMessage("Password successfully updated.");
    }

    private boolean hasRole(Roles role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_" + role.name()));
    }

    private String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
