package com.shadril.studentcrud.service;

import com.shadril.studentcrud.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService {
    List<Student> studentList = new ArrayList<>();
    @PostConstruct
    public void init(){
        studentList.add(new Student(101, "Shadril", "shadril@gmail.com", "10-09-2000", "Male", "01754402481", "Bashundhara R/A, Dhaka", "Information Systems", "20-01-2020", "04-09-2023", 1.00));
        studentList.add(new Student(102, "Mery", "mery@gmail.com", "27-08-2000", "Female", "01754502683", "Khilkhet, Dhaka", "Software Engineering", "20-01-2020", "04-09-2023", 3.86));
        studentList.add(new Student(103, "Gourob", "gourob@gmail.com", "20-01-1999", "Male", "01977723500", "Uttara, Dhaka", "Computation Theory", "28-08-2019", "04-09-2023", 3.56));
        studentList.add(new Student(104, "Pritom", "pritom@gmail.com", "17-04-1998", "Male", "01874122344", "Savar, Dhaka", "Software Engineering", "28-08-2019", "04-09-2023", 3.92));
        studentList.add(new Student(105, "Apon", "apon@gmail.com", "25-06-2000", "Male", "015433234543", "Banasree, Dhaka", "Information Systems", "21-04-2019", "04-09-2023", 4.00));
    }
    public Student findById(Integer id){
        for(Student student : studentList){
            if(Objects.equals(student.getId(), id)){
                return student;
            }
        }
        return null;
    }
    public List<Student> getAllStudent(){
        return studentList;
    }
    public void addStudent(Student student){
        Student existingStudent = findById(student.getId());
        if (existingStudent == null){
            studentList.add(student);
        }
    }
    public void removeStudent(Student student){
        Student existingStudent = findById(student.getId());
        if (existingStudent != null) {
            studentList.remove(existingStudent);
        }
    }
    public void updateStudent(Student updatedStudent) {
        Student existingStudent = findById(updatedStudent.getId());
        if (existingStudent != null) {
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
            existingStudent.setGender(updatedStudent.getGender());
            existingStudent.setPhoneNumber(updatedStudent.getPhoneNumber());
            existingStudent.setAddress(updatedStudent.getAddress());
            existingStudent.setMajor(updatedStudent.getMajor());
            existingStudent.setEnrollmentDate(updatedStudent.getEnrollmentDate());
            existingStudent.setGraduationDate(updatedStudent.getGraduationDate());
            existingStudent.setCgpa(updatedStudent.getCgpa());
        }
    }
}
