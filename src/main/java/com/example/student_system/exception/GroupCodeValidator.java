package com.example.student_system.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GroupCodeValidator implements ConstraintValidator<ValidGroupCode, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value.matches("[A-Z]{2}-\\d{2}");
    }
}