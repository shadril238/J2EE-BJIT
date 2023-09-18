package com.shadril.webfluxdemo.controller;

import com.shadril.webfluxdemo.model.Student;
import com.shadril.webfluxdemo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping(value = "/getall", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> studentFlux(){
        return studentService.studentFlux();
    }

    @PostMapping("/add")
    public Mono<Student> addStudentMono(@RequestBody Student student){
        return studentService.addStudentMono(student.getName(), student.getEmail());
    }

    @PutMapping("/update/{id}")
    public Mono<Student> updateStudentMono(@PathVariable UUID id, @RequestBody Student student) {
        return studentService.updateStudentMono(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteStudentMono(@PathVariable UUID id) {
        return studentService.deleteStudentMono(id);
    }

}
