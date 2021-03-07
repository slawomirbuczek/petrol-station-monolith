package com.pk.petrolstationmonolith.controllers.account;

import com.pk.petrolstationmonolith.dtos.account.EmployeeDto;
import com.pk.petrolstationmonolith.services.account.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/account/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('LPG_SERVICE','CAR_WASH','MONITORING','CASHIER','ADMIN','OWNER')")
    public ResponseEntity<EmployeeDto> getEmployee(Principal principal) {
        return ResponseEntity.ok(employeeService.getEmployeeDto(Long.parseLong(principal.getName())));
    }

    @GetMapping("/customers/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<EmployeeDto> getEmployeeDtoByUserId(@PathVariable long customerId) {
        return ResponseEntity.ok(employeeService.getEmployeeDto(customerId));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('LPG_SERVICE','CAR_WASH','MONITORING','CASHIER','ADMIN','OWNER')")
    public ResponseEntity<EmployeeDto> updateEmployee(Principal principal,
                                                    @Valid @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.updateEmployee(Long.parseLong(principal.getName()), employeeDto));
    }

    @PutMapping("/customers/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<EmployeeDto> updateEmployeeByUserId(@PathVariable long customerId,
                                                            @Valid @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.updateEmployee(customerId, employeeDto));
    }

}
