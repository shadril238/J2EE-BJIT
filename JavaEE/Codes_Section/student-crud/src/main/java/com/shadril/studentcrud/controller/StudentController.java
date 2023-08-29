package com.shadril.studentcrud.controller;

import com.shadril.studentcrud.model.Student;
import com.shadril.studentcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public ModelAndView homePage(){
        ModelAndView modelAndView = new ModelAndView("home-page.html");
        return modelAndView;
    }

    @GetMapping("/list-all-students")
    public ModelAndView listAllStudents(){
        ModelAndView modelAndView = new ModelAndView("all-students-page.html");
        modelAndView.addObject("studentList", studentService.getAllStudent());
        return modelAndView;
    }

    @DeleteMapping("/remove-student/{id}")
    public ModelAndView removeStudent(@PathVariable Integer id) {
        studentService.removeStudent(id);
        return new ModelAndView("redirect:/list-all-students");
    }

    @GetMapping("/add-student")
    public ModelAndView addStudent(){
        return new ModelAndView("add-student-page.html");
    }

    @PostMapping("/add-student")
    public ModelAndView addStudent(@ModelAttribute Student student){
        studentService.addStudent(student);
        return new ModelAndView("redirect:/list-all-students");
    }

    @GetMapping("/update-student/{id}")
    public ModelAndView updateStudentPage(@PathVariable Integer id) {
        Student student = studentService.findById(id);
        ModelAndView modelAndView = new ModelAndView("update-student-page.html");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PostMapping("/update-student")
    public ModelAndView updateStudent(@ModelAttribute Student student) {
        studentService.updateStudent(student);
        return new ModelAndView("redirect:/list-all-students");
    }


}