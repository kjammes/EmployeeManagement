package com.employee.EmployeeManagement.repo;

import com.employee.EmployeeManagement.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
