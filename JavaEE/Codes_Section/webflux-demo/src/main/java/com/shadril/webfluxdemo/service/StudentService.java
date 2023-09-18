package com.shadril.webfluxdemo.service;

import com.shadril.webfluxdemo.dao.StudentDao;
import com.shadril.webfluxdemo.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentDao studentDao;
//    public List<Student> students (){
//        return studentDao.getStudents();
//    }
//
//    public Student addStudent(String name, String email){
//        return studentDao.addStudent(name, email);
//    }

    public Flux<Student> studentFlux (){
        return studentDao.getStudentFlux();
    }

    public Mono<Student> addStudentMono(String name, String email){
        return studentDao.addStudentMono(name, email);
    }

    public Mono<Student> updateStudentMono(UUID id, Student student) {
        return studentDao.updateStudentMono(id, student);
    }

    public Mono<Void> deleteStudentMono(UUID id) {
        return studentDao.deleteStudentMono(id);
    }
}
