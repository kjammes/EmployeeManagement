package com.employee.EmployeeManagement.service_implementations;

import com.employee.EmployeeManagement.exception.ResourceNotFoundException;
import com.employee.EmployeeManagement.models.Employee;
import com.employee.EmployeeManagement.repo.EmployeeRepo;
import com.employee.EmployeeManagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public Employee create(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getById(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id)
        );
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee oldEmployee, Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", oldEmployee.getId())
        );

        employee.setFirstName(oldEmployee.getFirstName());
        employee.setLastName(oldEmployee.getLastName());
        employee.setEmail(oldEmployee.getEmail());

        return employeeRepo.save(employee);
    }

    @Override
    public Employee deleteEmployee(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id)
        );

        employeeRepo.deleteById(id);
        return employee;
    }
}
