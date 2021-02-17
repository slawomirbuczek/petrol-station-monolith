package com.pk.petrolstationmonolith.repositories.user;

import com.pk.petrolstationmonolith.entities.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
