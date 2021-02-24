package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.exceptions.account.EmailAlreadyTakenException;
import com.pk.petrolstationmonolith.models.account.UserCredentials;
import com.pk.petrolstationmonolith.models.account.registration.CompanyRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.EmployeeRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.IndividualRegistrationCredentials;
import com.pk.petrolstationmonolith.repositories.account.AddressRepository;
import com.pk.petrolstationmonolith.repositories.account.CompanyRepository;
import com.pk.petrolstationmonolith.repositories.account.EmployeeRepository;
import com.pk.petrolstationmonolith.repositories.account.IndividualRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTests {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private IndividualRepository individualRepository;

    @Mock
    private UserService userService;

    @Spy
    private static PasswordEncoder encoder;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeAll
    static void setUp() {
        encoder = new BCryptPasswordEncoder();
    }

    @Test
    void shouldReturnUserCredentialsWhenIndividualCredentialsAreValid() {
        given(userService.emailAlreadyTaken(any())).willReturn(false);
        given(addressRepository.save(any())).willAnswer(i -> i.getArgument(0));
        given(individualRepository.save(any())).willAnswer(i -> i.getArgument(0));
        given(userService.saveUser(any())).willAnswer(i -> {
            User user = i.getArgument(0, User.class);
            user.setId(1L);
            return user;
        });

        UserCredentials credentials = registrationService.registerIndividual(getIndividualRegistrationCredentials());

        assertThat(credentials.getUsername()).isEqualTo("1");
        assertThat(credentials.getPassword()).isNotBlank();
    }

    @Test
    void shouldThrowEmailAlreadyTakenExceptionWhenIndividualEmailIsTaken() {
        given(userService.emailAlreadyTaken(any())).willReturn(true);

        assertThatThrownBy(() -> registrationService.registerIndividual(getIndividualRegistrationCredentials()))
                .isInstanceOf(EmailAlreadyTakenException.class)
                .hasMessage("Email: " + getIndividualRegistrationCredentials().getEmail() + " is already taken");
    }

    @Test
    void shouldReturnUserCredentialsWhenCompanyCredentialsAreValid() {
        given(userService.emailAlreadyTaken(any())).willReturn(false);
        given(addressRepository.save(any())).willAnswer(i -> i.getArgument(0));
        given(companyRepository.save(any())).willAnswer(i -> i.getArgument(0));
        given(userService.saveUser(any())).willAnswer(i -> {
            User user = i.getArgument(0, User.class);
            user.setId(1L);
            return user;
        });

        UserCredentials credentials = registrationService.registerCompany(getCompanyRegistrationCredentials());

        assertThat(credentials.getUsername()).isEqualTo("1");
        assertThat(credentials.getPassword()).isNotBlank();
    }

    @Test
    void shouldThrowEmailAlreadyTakenExceptionWhenCompanyEmailIsTaken() {
        given(userService.emailAlreadyTaken(any())).willReturn(true);

        assertThatThrownBy(() -> registrationService.registerCompany(getCompanyRegistrationCredentials()))
                .isInstanceOf(EmailAlreadyTakenException.class)
                .hasMessage("Email: " + getCompanyRegistrationCredentials().getEmail() + " is already taken");
    }

    @Test
    void shouldReturnUserCredentialsWhenEmployeeCredentialsAreValid() {
        given(userService.emailAlreadyTaken(any())).willReturn(false);
        given(addressRepository.save(any())).willAnswer(i -> i.getArgument(0));
        given(employeeRepository.save(any())).willAnswer(i -> i.getArgument(0));
        given(userService.saveUser(any())).willAnswer(i -> {
            User user = i.getArgument(0, User.class);
            user.setId(1L);
            return user;
        });

        UserCredentials credentials = registrationService.registerEmployee(getEmployeeRegistrationCredentials());

        assertThat(credentials.getUsername()).isEqualTo("1");
        assertThat(credentials.getPassword()).isNotBlank();
    }

    @Test
    void shouldThrowEmailAlreadyTakenExceptionWhenEmployeeEmailIsTaken() {
        given(userService.emailAlreadyTaken(any())).willReturn(true);

        assertThatThrownBy(() -> registrationService.registerEmployee(getEmployeeRegistrationCredentials()))
                .isInstanceOf(EmailAlreadyTakenException.class)
                .hasMessage("Email: " + getEmployeeRegistrationCredentials().getEmail() + " is already taken");
    }

    private IndividualRegistrationCredentials getIndividualRegistrationCredentials() {
        return new IndividualRegistrationCredentials("Jan", "Kowalski", "jankowalski@gmail.com",
                "15301224419", "7629382099", "Poland", "Kraków", "30-063", "3 Maja Al.",
                "15", "207A");
    }

    private CompanyRegistrationCredentials getCompanyRegistrationCredentials() {
        return new CompanyRegistrationCredentials("Evil Comp", "evilcomp@email.com",
                "056034722", "7629382099", "Poland", "Kraków", "30-063", "3 Maja Al.",
                "15", "207A");
    }

    private EmployeeRegistrationCredentials getEmployeeRegistrationCredentials() {
        return new EmployeeRegistrationCredentials("Jan", "Kowalski", "jankowalski@gmail.com",
                "15301224419", "7629382099", "123456789", Date.valueOf("1990-01-01"), 15000,
                "PL73109014768083997739562290", "2000-01-01", "Poland", "Kraków",
                "30-063", "3 Maja Al.", "15", "207A");
    }

}
