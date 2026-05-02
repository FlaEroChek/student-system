package com.example.student_system.repository;

import com.example.student_system.model.Student;
import com.example.student_system.model.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    Optional<Student> findByEmail(String email);

    long countByGroupNameAndStatus(String groupName, StudentStatus status);

    List<Student> findByGroupNameAndStatus(String groupName, StudentStatus status);
}
