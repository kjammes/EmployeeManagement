package com.employee.EmployeeManagement.controllers;

import com.employee.EmployeeManagement.exception.ResourceNotFoundException;
import com.employee.EmployeeManagement.models.Employee;
import com.employee.EmployeeManagement.service_implementations.EmployeeServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeServiceImplementation employeeService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Employee employee) {
        log.info("[EmployeeController] Start of create method");
        Employee createdEmployee = null;
        try {
            log.info("Creating employee using employee service");
            createdEmployee = employeeService.create(employee);
        } catch (Exception e) {
            log.error("Error while storing employee to DB");
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>("Please send all fields required and email should be unqiue", HttpStatus.BAD_REQUEST);
        }

        if (createdEmployee == null) {
            log.error("[EmployeeController] Created employee is NULL");
            return ResponseEntity.notFound().build();
        }

        log.info("[EmployeeController] Successfully returning the created employee");
        return new ResponseEntity<>(String.valueOf(createdEmployee.getId()), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> findAll() {
        log.info("[EmployeeController] Getting all the employees");
        return  new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") Long id) {
        Employee employee = null;
        try {
            employee = employeeService.getById(id);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            log.error(resourceNotFoundException.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(employee, id);
            return ResponseEntity.ok(updatedEmployee);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            log.error(resourceNotFoundException.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
        try {
            Employee deletedEmployee = employeeService.deleteEmployee(id);
            return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
