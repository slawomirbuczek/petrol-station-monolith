package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.entities.account.Address;
import com.pk.petrolstationmonolith.entities.account.Employee;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.enums.account.Roles;
import com.pk.petrolstationmonolith.enums.account.UserType;
import com.pk.petrolstationmonolith.repositories.account.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Date;

@Component
public class DefaultUsers {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CompanyRepository companyRepository;
    private final IndividualRepository individualRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultUsers(UserRepository userRepository, AddressRepository addressRepository,
                        CompanyRepository companyRepository, IndividualRepository individualRepository,
                        EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.companyRepository = companyRepository;
        this.individualRepository = individualRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void addDefaultUsers() {
        User janKowalski = new User(1L, passwordEncoder.encode("kowalski"),
                "jankowalski@gmail.com", Roles.OWNER, UserType.EMPLOYEE);
        janKowalski = userRepository.save(janKowalski);

        Address addressJanKowalski = addressRepository.save(new Address(1L, "Poland", "Kraków",
                "30-063", "3 Maja Al.", "15", "207A"));

        employeeRepository.save(new Employee(1L, "Jan", "Kowalski", "15301224419", "7629382099", "123456789",
                Date.valueOf("1990-01-01"), 15000, "PL73109014768083997739562290", "2000-01-01",
                janKowalski, addressJanKowalski));


        User zbigniewZero = new User(2L, passwordEncoder.encode("zero"),
                "zbigniewZero@gmail.com", Roles.ADMIN, UserType.EMPLOYEE);
        zbigniewZero = userRepository.save(zbigniewZero);

        Address addressZbigniewZero = addressRepository.save(new Address(2L, "Poland", "Warszawa",
                "30-063", "Warszawska", "12", "14"));

        employeeRepository.save(new Employee(2L, "Zbigniew", "Zero", "36080477898", "9719662751", "123765789",
                Date.valueOf("1990-01-01"), 9000, "PL73935410202944519044050628", "2000-01-01",
                zbigniewZero, addressZbigniewZero));


        User tomaszNowak = new User(3L, passwordEncoder.encode("nowak"),
                "aix24557@cuoly.com", Roles.CASHIER, UserType.EMPLOYEE);
        tomaszNowak = userRepository.save(tomaszNowak);

        Address addressTomaszNowak = addressRepository.save(new Address(3L, "Poland", "Kraków",
                "30-063", "Warszawska", "23", "2207C"));

        employeeRepository.save(new Employee(3L, "Tomasz", "Nowak", "17252723968", "1081306978", "321123789",
                Date.valueOf("1996-01-26"), 4000, "PL41876210190000761639083626", "2010-03-01",
                tomaszNowak, addressTomaszNowak));


        User karolKowalczyk = new User(4L, passwordEncoder.encode("kowalczyk"),
                "karolKowalczyk@gmail.com", Roles.CASHIER, UserType.EMPLOYEE);
        karolKowalczyk = userRepository.save(karolKowalczyk);

        Address addressKarolKowalczyk = addressRepository.save(new Address(4L, "Poland", "Kraków",
                "30-063", "Warszawska", "124", "3107C"));

        employeeRepository.save(new Employee(4L, "Karol", "Kowalczyk", "07100305954", "1163426157", "321123459",
                Date.valueOf("1993-12-21"), 5000, "PL75815100014087171075178326", "2006-02-01",
                karolKowalczyk, addressKarolKowalczyk));




        /*userRepository.save(new User("wincenty", passwordEncoder.encode("raczek"), Roles.MONITORING, UserType.EMPLOYEE));
        userRepository.save(new User("eugeniusz", passwordEncoder.encode("zuczek"), Roles.MONITORING, UserType.EMPLOYEE));

        userRepository.save(new User("alojzy", passwordEncoder.encode("mydlak"), Roles.CAR_WASH, UserType.EMPLOYEE));
        userRepository.save(new User("baltazar", passwordEncoder.encode("gabka"), Roles.CAR_WASH, UserType.EMPLOYEE));

        userRepository.save(new User("czeslaw", passwordEncoder.encode("ratajczak"), Roles.LPG_SERVICE, UserType.EMPLOYEE));
        userRepository.save(new User("radoslaw", passwordEncoder.encode("rataj"), Roles.LPG_SERVICE, UserType.EMPLOYEE));

        userRepository.save(new User("Anon", passwordEncoder.encode("password"), Roles.USER, UserType.EMPLOYEE));*/
    }


}
