package com.employee.EmployeeManagement.services;

import com.employee.EmployeeManagement.models.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    List<Employee> getAllEmployees();
    Employee getById(Long id);
    Employee updateEmployee(Employee employee, Long id);
    Employee deleteEmployee(Long id);
}
