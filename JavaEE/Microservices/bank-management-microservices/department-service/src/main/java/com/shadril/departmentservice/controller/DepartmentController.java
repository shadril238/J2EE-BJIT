package com.shadril.departmentservice.controller;

import com.shadril.departmentservice.client.EmployeeClient;
import com.shadril.departmentservice.model.Department;
import com.shadril.departmentservice.repository.DepartmentRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping
    public Department add(@RequestBody Department department){
        return repository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id){
        return repository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees(){
        List<Department> departments
                = repository.findAll();
        departments.forEach(department ->
                department.setEmployees(
                        employeeClient.findByDepartment(
                                department.getId())));
        return departments;
    }
}
