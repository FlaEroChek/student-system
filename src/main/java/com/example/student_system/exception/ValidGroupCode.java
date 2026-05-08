package com.example.student_system.exception;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GroupCodeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGroupCode { String message() default "Invalid group code format. Expected: IT-12, CS-99";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
