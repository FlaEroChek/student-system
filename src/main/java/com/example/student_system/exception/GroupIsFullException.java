package com.example.student_system.exception;

public class GroupIsFullException extends RuntimeException {
    public GroupIsFullException(String message) {
        super(message);
    }
}
