package com.example.student_system.service;

import com.example.student_system.exception.DuplicateEmailException;
import com.example.student_system.exception.GroupIsFullException;
import lombok.RequiredArgsConstructor;
import com.example.student_system.model.Student;
import com.example.student_system.model.StudentStatus;
import org.springframework.stereotype.Service;
import com.example.student_system.repository.StudentRepository;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public Student create(Student student) {

        repository.findByEmail(student.getEmail())
                .ifPresent(s -> {
                    throw new DuplicateEmailException("Email already exists");
                });

        long count = repository.countByGroupNameAndStatus(
                student.getGroupName(),
                StudentStatus.ACTIVE);

        if (count >= 5) {
            throw new GroupIsFullException("Group is full");
        }

        student.setStatus(StudentStatus.ACTIVE);

        return repository.save(student);
    }

    public List<Student> getAll(String groupName,
                                StudentStatus status) {

        if (groupName != null && status != null) {
            return repository.findByGroupNameAndStatus(groupName, status);
        }

        return repository.findAll();
    }

    public void softDelete(UUID id) {

        Student student = repository.findById(id)
                .orElseThrow();

        if (student.getStatus() == StudentStatus.DELETED) {
            throw new IllegalStateException("Already deleted");
        }

        student.setStatus(StudentStatus.DELETED);

        repository.save(student);
    }

}
