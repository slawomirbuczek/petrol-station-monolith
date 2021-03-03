package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.EmployeeDto;
import com.pk.petrolstationmonolith.entities.account.Employee;
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

    public void addEmployee(Employee employee) {
        log.trace("Adding new employee for user with id " + employee.getUser().getId());
        employeeRepository.save(employee);
    }

    public EmployeeDto updateEmployee(long userId, EmployeeDto employeeDto) {
        log.trace("Updating employee for user with id " + userId);
        Employee oldEmployee = getEmployee(userId);

        Employee employee = mapDtoToEmployee(employeeDto);
        employee.setId(oldEmployee.getId());
        employee.setUser(oldEmployee.getUser());
        employee = employeeRepository.save(employee);

        return mapEmployeeToDto(employee);
    }

    public EmployeeDto deleteEmployee(long userId) {
        log.trace("Deleting address for user with id " + userId);
        Employee employee = getEmployee(userId);
        employeeRepository.delete(employee);
        return mapEmployeeToDto(employee);
    }

    private Employee getEmployee(long userId) {
        return employeeRepository.findByUserId(userId)
                .orElseThrow(() -> new EmployeeNotFoundException(userId));
    }

    private EmployeeDto mapEmployeeToDto(Employee employee) {
        return mapper.map(employee, EmployeeDto.class);
    }

    private Employee mapDtoToEmployee(EmployeeDto employeeDto) {
        return mapper.map(employeeDto, Employee.class);
    }

}
