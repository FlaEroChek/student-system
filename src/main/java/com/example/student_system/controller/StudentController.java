package com.example.student_system.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.student_system.model.Student;
import com.example.student_system.model.StudentStatus;
import org.springframework.web.bind.annotation.*;
import com.example.student_system.service.StudentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    public Student create(@RequestBody @Valid Student student) {
        return service.create(student);
    }

    @GetMapping
    public List<Student> getAll(
        @RequestParam(required = false) String groupName,
        @RequestParam(required = false)StudentStatus status
    ){
        return service.getAll(groupName, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        service.softDelete(id);
    }



}
