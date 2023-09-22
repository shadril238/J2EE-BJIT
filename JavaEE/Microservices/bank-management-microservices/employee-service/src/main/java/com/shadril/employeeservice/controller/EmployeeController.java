package com.shadril.employeeservice.controller;

import com.shadril.employeeservice.model.Employee;
import com.shadril.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository repository;

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        return repository.add(employee);
    }

    @GetMapping
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        return repository.findByDepartment(departmentId);
    }
}
