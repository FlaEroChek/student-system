package com.example.student_system;


import com.example.student_system.exception.DuplicateEmailException;
import com.example.student_system.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.student_system.repository.StudentRepository;
import com.example.student_system.service.StudentService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository repository;

    @InjectMocks
    StudentService service;

    @Test
    void shouldThrowDuplicateEmail() {

        Student student = new Student();
        student.setEmail("test@mail.com");

        when(repository.findByEmail("test@mail.com"))
                .thenReturn(Optional.of(new Student()));

        assertThrows(DuplicateEmailException.class,
                () -> service.create(student));
    }
}