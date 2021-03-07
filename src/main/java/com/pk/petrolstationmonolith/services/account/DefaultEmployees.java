package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.entities.account.Addresses;
import com.pk.petrolstationmonolith.entities.account.Employees;
import com.pk.petrolstationmonolith.entities.account.Individuals;
import com.pk.petrolstationmonolith.entities.account.Customers;
import com.pk.petrolstationmonolith.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@AllArgsConstructor
@Slf4j
public class DefaultEmployees {

    private final CustomerService customerService;
    private final AddressService addressService;
    private final IndividualService individualService;
    private final EmployeeService employeeService;

    @PostConstruct
    private void addDefaultCustomers() {
        log.trace("Adding default customers");

        addEmployee(1L, "kowalski", "pk.petrolstation@gmail.com", Roles.OWNER,
                "Kraków", "30-063", "3 Maja Al.", "15", "207A",
                "Jan", "Kowalski", "15301224419", "7629382099", "123456789",
                LocalDate.of(1981, 1, 1), 15000, "PL73109014768083997739562290",
                LocalDate.of(2000, 1, 1));


        addEmployee(2L, "zero", "zbigniewzero@gmail.com", Roles.ADMIN,
                "Warszawa", "30-063", "Warszawska", "12", "14",
                "Zbigniew", "Zero", "36080477898", "9719662751", "123765789",
                LocalDate.of(1980, 1, 1), 9000, "PL73935410202944519044050628",
                LocalDate.of(2000, 1, 1));

        addEmployee(3L, "nowak", "tomasznowak@cuoly.com", Roles.CASHIER,
                "Kraków", "30-063", "Warszawska", "23", "2207C",
                "Tomasz", "Nowak", "17252723968", "1081306978", "321123789",
                LocalDate.of(1980, 1, 1), 4000, "PL41876210190000761639083626",
                LocalDate.of(2000, 1, 1));

        addEmployee(4L, "kowalczyk", "karolkowalczyk@gmail.com", Roles.CASHIER,
                "Kraków", "30-063", "Warszawska", "124", "3107C",
                "Karol", "Kowalczyk", "07100305954", "1163426157", "321123459",
                LocalDate.of(1980, 1, 1), 5000, "PL75815100014087171075178326",
                LocalDate.of(2000, 1, 1));

        addEmployee(5L, "raczek", "wincentyraczek@testmail.com", Roles.MONITORING,
                "Katowice", "40-001", "Krakowska", "123", "123",
                "Wincenty", "Raczek", "09080918012", "7743201136", "123123123",
                LocalDate.of(1999, 5, 5), 3500, "PL22820010189463966477011120",
                LocalDate.of(2020, 3, 3));

        addEmployee(6L, "zuczek", "eugeniuszzuczek@testmail.com", Roles.MONITORING,
                "Kraków", "30-063", "Warszawska", "23", "2207C",
                "Eugeniusz", "Żuczek", "10032079765", "3414875819", "321123789",
                LocalDate.of(1998, 1, 1), 4000, "PL98102046493859092155604682",
                LocalDate.of(2017, 2, 1));

        addEmployee(7L, "mydlak", "alojzymydlak@testmail.com", Roles.CAR_WASH,
                "Kraków", "30-063", "Warszawska", "23", "2207C",
                "Alojzy", "Mydlak", "64082259563", "9525970180", "321123789",
                LocalDate.of(1998, 1, 1), 3000, "PL23103012188320529518438007",
                LocalDate.of(2017, 2, 1));

        addEmployee(8L, "gabka", "baltazargabka@testmail.com", Roles.CAR_WASH,
                "Kraków", "30-063", "Warszawska", "23", "2207C",
                "Baltazar", "Gąbka", "14072431659", "9524908859", "321123789",
                LocalDate.of(1998, 1, 1), 3000, "PL22101000004617782885108273",
                LocalDate.of(2017, 2, 1));


        addEmployee(9L, "ratajczak", "czeslawratajczak@testmail.com", Roles.LPG_SERVICE,
                "Kraków", "30-063", "Warszawska", "23", "2207C",
                "Czesław", "Ratajczak", "22021558780", "7620175973", "321123789",
                LocalDate.of(1998, 1, 1), 3000, "PL98907500099661392189199554",
                LocalDate.of(2017, 2, 1));

        addEmployee(10L, "rataj", "radoslawrataj@testmail.com", Roles.LPG_SERVICE,
                "Kraków", "30-063", "Warszawska", "23", "2207C",
                "Radosław", "Rataj", "41041085210", "1086591162", "321123789",
                LocalDate.of(1998, 1, 1), 3000, "PL06812610337733474715136641",
                LocalDate.of(2017, 2, 1));

    }

    private void addEmployee(Long id, String password, String email, Roles role,
                             String city, String zip, String street, String buildingNumber, String apartmentNumber,
                             String firstName, String lastName, String pesel, String nip, String phoneNumber,
                             LocalDate dateOfBirth, int salary, String accountNumber, LocalDate startDateOfWork) {

        Customers customer = new Customers(id, password, email, role);
        customer = customerService.addCustomer(customer);

        Addresses address = new Addresses("Polska", city, zip, street, buildingNumber, apartmentNumber, customer);
        addressService.addAddress(address);

        Individuals individuals = new Individuals(firstName, lastName, pesel, nip, customer);
        individualService.addIndividual(individuals);

        Employees employees = new Employees(phoneNumber, dateOfBirth, salary, accountNumber, startDateOfWork, customer);
        employeeService.addEmployee(employees);
    }

}
