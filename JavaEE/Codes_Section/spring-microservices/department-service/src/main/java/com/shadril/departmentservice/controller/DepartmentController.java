package com.shadril.departmentservice.controller;

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

    private static final Logger LOGGER
            = (Logger) LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository repository;

//    @Autowired
//    private EmployeeClient employeeClient;

    @PostMapping
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department add: {department}");
        return repository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll() {
        LOGGER.info("Department find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        LOGGER.info("Department find: id={}" + id);
        return repository.findById(id);
    }

}
