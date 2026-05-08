package com.example.student_system.model;

import com.example.student_system.exception.ValidGroupCode;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.student_system.model.StudentStatus;
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

    @NotBlank
    @Size(min = 5)
    private String fullName;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    @ValidGroupCode
    private String groupName;

    @Min(0)
    @Max(100)
    private double averageGrade;

    @Enumerated(EnumType.STRING)
    private StudentStatus status = StudentStatus.ACTIVE;
}
