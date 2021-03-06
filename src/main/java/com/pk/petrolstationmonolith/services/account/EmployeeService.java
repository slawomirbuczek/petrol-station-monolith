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

    public EmployeeDto getEmployeeDto(long userId) {
        log.trace("Getting employee dto for user with id " + userId);
        return mapEmployeeToDto(getEmployee(userId));
    }

    public void addEmployee(Employees employees) {
        log.trace("Adding new employee for user with id " + employees.getCustomers().getId());
        employeeRepository.save(employees);
    }

    public EmployeeDto updateEmployee(long userId, EmployeeDto employeeDto) {
        log.trace("Updating employee for user with id " + userId);
        Employees oldEmployees = getEmployee(userId);

        Employees employees = mapDtoToEmployee(employeeDto);
        employees.setId(oldEmployees.getId());
        employees.setCustomers(oldEmployees.getCustomers());
        employees = employeeRepository.save(employees);

        return mapEmployeeToDto(employees);
    }

    public EmployeeDto deleteEmployee(long userId) {
        log.trace("Deleting address for user with id " + userId);
        Employees employees = getEmployee(userId);
        employeeRepository.delete(employees);
        return mapEmployeeToDto(employees);
    }

    private Employees getEmployee(long userId) {
        return employeeRepository.findByUserId(userId)
                .orElseThrow(() -> new EmployeeNotFoundException(userId));
    }

    private EmployeeDto mapEmployeeToDto(Employees employees) {
        return mapper.map(employees, EmployeeDto.class);
    }

    private Employees mapDtoToEmployee(EmployeeDto employeeDto) {
        return mapper.map(employeeDto, Employees.class);
    }

}
