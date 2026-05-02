package com.example.student_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Student {
    @Id
    @GeneratedValue
    private UUID id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String groupName;

    private double averageGrade;

    @Enumerated(EnumType.STRING)
    private StudentStatus status = StudentStatus.ACTIVE;
}
