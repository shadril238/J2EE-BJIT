package com.shadril.webfluxdemo.dao;

import com.shadril.webfluxdemo.model.Student;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class StudentDao {
    private Flux<Student> studentFlux = Flux.empty();

    public Flux<Student> getStudentFlux(){
        return studentFlux;
    }

    public Mono<Student> addStudentMono(String name, String email){
        UUID id = UUID.randomUUID();
        Student student = new Student(id, name, email);
        studentFlux = studentFlux.concatWithValues(student);
        return Mono.just(student);
    }

    public Mono<Student> updateStudentMono(UUID id, Student updatedStudent) {
        return studentFlux
                .filter(student -> student.getId().equals(id))
                .next()
                .doOnNext(student -> {
                    student.setName(updatedStudent.getName());
                    student.setEmail(updatedStudent.getEmail());
                });
    }

    public Mono<Void> deleteStudentMono(UUID id) {
        studentFlux = studentFlux.filter(student -> !student.getId().equals(id));
        return Mono.empty();
    }


}