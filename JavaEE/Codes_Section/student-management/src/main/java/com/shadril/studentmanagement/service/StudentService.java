package com.shadril.studentmanagement.service;

import com.shadril.studentmanagement.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    List<Student> studentList = new ArrayList<>();
    @PostConstruct
    public void init(){
        studentList.add(new Student(101, "Shadril", "shadril@gmail.com", "10-09-2000", "Male", "01754402481", "Bashundhara R/A, Dhaka", "Information Systems", "20-01-2020", "04-09-2023", 3.94));
    }
}
