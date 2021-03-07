package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.EmployeeDto;
import com.pk.petrolstationmonolith.entities.account.Employees;
import com.pk.petrolstationmonolith.exceptions.account.employee.EmployeeNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeDto getEmployeeDto(long customerId) {
        log.trace("Getting employee dto for customer with id " + customerId);
        return mapEmployeeToDto(getEmployee(customerId));
    }

    public void addEmployee(Employees employees) {
        log.trace("Adding new employee for customer with id " + employees.getCustomers().getId());
        employeeRepository.save(employees);
    }

    public EmployeeDto updateEmployee(long customerId, EmployeeDto employeeDto) {
        log.trace("Updating employee for customer with id " + customerId);
        Employees oldEmployees = getEmployee(customerId);

        Employees employees = mapDtoToEmployee(employeeDto);
        employees.setId(oldEmployees.getId());
        employees.setCustomers(oldEmployees.getCustomers());
        employees = employeeRepository.save(employees);

        return mapEmployeeToDto(employees);
    }

    public EmployeeDto deleteEmployee(long customerId) {
        log.trace("Deleting address for customer with id " + customerId);
        Employees employees = getEmployee(customerId);
        employeeRepository.delete(employees);
        return mapEmployeeToDto(employees);
    }

    private Employees getEmployee(long customerId) {
        return employeeRepository.findByCustomersId(customerId)
                .orElseThrow(() -> new EmployeeNotFoundException(customerId));
    }

    private EmployeeDto mapEmployeeToDto(Employees employees) {
        return mapper.map(employees, EmployeeDto.class);
    }

    private Employees mapDtoToEmployee(EmployeeDto employeeDto) {
        return mapper.map(employeeDto, Employees.class);
    }

}
