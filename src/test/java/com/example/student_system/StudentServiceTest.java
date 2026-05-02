package com.example.student_system;


import com.example.student_system.exception.DuplicateEmailException;
import com.example.student_system.exception.GroupIsFullException;
import com.example.student_system.model.Student;
import com.example.student_system.model.StudentStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.student_system.repository.StudentRepository;
import com.example.student_system.service.StudentService;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    void shouldThrowGroupIsFullException() {
        Student student = new Student();
        student.setEmail("newstudent@mail.com");
        student.setGroupName("KI-31");

        when(repository.findByEmail("newstudent@mail.com"))
                .thenReturn(Optional.empty());
        when(repository.countByGroupNameAndStatus("KI-31", StudentStatus.ACTIVE))
                .thenReturn(5L);

        assertThrows(GroupIsFullException.class,
                () -> service.create(student));
    }

    @Test
    void shouldCreateStudentSuccessfully() {
        Student student = new Student();
        student.setFullName("Ivan Petrenko");
        student.setEmail("ivan@mail.com");
        student.setGroupName("KI-31");
        student.setAverageGrade(4.5);

        when(repository.findByEmail("ivan@mail.com"))
                .thenReturn(Optional.empty());
        when(repository.countByGroupNameAndStatus("KI-31", StudentStatus.ACTIVE))
                .thenReturn(2L);
        when(repository.save(student))
                .thenReturn(student);

        Student result = service.create(student);

        assertThat(result.getStatus()).isEqualTo(StudentStatus.ACTIVE);
    }

    @Test
    void shouldThrowWhenDeletingAlreadyDeletedStudent() {
        Student student = new Student();
        student.setStatus(StudentStatus.DELETED);

        when(repository.findById(any(UUID.class)))
                .thenReturn(Optional.of(student));

        assertThrows(IllegalStateException.class,
                () -> service.softDelete(UUID.randomUUID()));
    }
}