package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.EmployeeDto;
import com.pk.petrolstationmonolith.entities.account.Employee;
import com.pk.petrolstationmonolith.exceptions.account.employee.EmployeeNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public EmployeeDto getEmployeeDto(long userId) {
        return mapEmployeeToDto(getEmployee(userId));
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public EmployeeDto updateEmployee(long userId, EmployeeDto employeeDto) {
        Employee oldEmployee = getEmployee(userId);

        Employee employee = mapDtoToEmployee(employeeDto);
        employee.setId(oldEmployee.getId());
        employee.setUser(oldEmployee.getUser());
        employee = employeeRepository.save(employee);

        return mapEmployeeToDto(employee);
    }

    public EmployeeDto deleteEmployee(long userId) {
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
