package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.entities.account.Address;
import com.pk.petrolstationmonolith.entities.account.Employee;
import com.pk.petrolstationmonolith.entities.account.Individual;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.enums.account.Roles;
import com.pk.petrolstationmonolith.enums.account.UserType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Date;

@Component
@AllArgsConstructor
public class DefaultUsers {

    private final UserService userService;
    private final AddressService addressService;
    private final IndividualService individualService;
    private final EmployeeService employeeService;

    @PostConstruct
    private void addDefaultUsers() {

        addEmployee(1L, "kowalski", "jankowalski@gmail.com", Roles.OWNER,
                "Poland", "Kraków", "30-063", "3 Maja Al.", "15", "207A",
                "Jan", "Kowalski", "15301224419", "7629382099", "123456789",
                Date.valueOf("1990-01-01"), 15000, "PL73109014768083997739562290", "2000-01-01");


        addEmployee(2L, "zero", "zbigniewZero@gmail.com", Roles.ADMIN, "Poland",
                "Warszawa", "30-063", "Warszawska", "12", "14", "Zbigniew",
                "Zero", "36080477898", "9719662751", "123765789", Date.valueOf("1990-01-01"),
                9000, "PL73935410202944519044050628", "2000-01-01");

        addEmployee(3L, "nowak", "aix24557@cuoly.com", Roles.CASHIER,
                "Poland", "Kraków", "30-063", "Warszawska", "23", "2207C",
                "Tomasz", "Nowak", "17252723968", "1081306978", "321123789",
                Date.valueOf("1996-01-26"), 4000, "PL41876210190000761639083626", "2010-03-01");


        addEmployee(4L, "kowalczyk", "karolKowalczyk@gmail.com", Roles.CASHIER,
                "Poland", "Kraków", "30-063", "Warszawska", "124", "3107C",
                "Karol", "Kowalczyk", "07100305954", "1163426157", "321123459",
                Date.valueOf("1993-12-21"), 5000, "PL75815100014087171075178326", "2006-02-01");

        /*userRepository.save(new User("wincenty", passwordEncoder.encode("raczek"), Roles.MONITORING, UserType.EMPLOYEE));
        userRepository.save(new User("eugeniusz", passwordEncoder.encode("zuczek"), Roles.MONITORING, UserType.EMPLOYEE));

        userRepository.save(new User("alojzy", passwordEncoder.encode("mydlak"), Roles.CAR_WASH, UserType.EMPLOYEE));
        userRepository.save(new User("baltazar", passwordEncoder.encode("gabka"), Roles.CAR_WASH, UserType.EMPLOYEE));

        userRepository.save(new User("czeslaw", passwordEncoder.encode("ratajczak"), Roles.LPG_SERVICE, UserType.EMPLOYEE));
        userRepository.save(new User("radoslaw", passwordEncoder.encode("rataj"), Roles.LPG_SERVICE, UserType.EMPLOYEE));

        userRepository.save(new User("Anon", passwordEncoder.encode("password"), Roles.USER, UserType.EMPLOYEE));*/
    }

    private void addEmployee(Long id, String password, String email, Roles role, String country,
                             String city, String zip, String street, String buildingNumber, String apartmentNumber,
                             String firstName, String lastName, String pesel, String nip, String phoneNumber,
                             Date dateOfBirth, int salary, String accountNumber, String startDateOfWork) {

        User user = new User(id, password, email, role, UserType.EMPLOYEE);
        user = userService.addUser(user);

        Address address = new Address(country, city, zip, street, buildingNumber, apartmentNumber, user);
        addressService.addAddress(address);

        Individual individual = new Individual(firstName, lastName, pesel, nip, user);
        individualService.addIndividual(individual);

        Employee employee = new Employee(phoneNumber, dateOfBirth, salary, accountNumber, startDateOfWork, user);
        employeeService.addEmployee(employee);
    }


}
