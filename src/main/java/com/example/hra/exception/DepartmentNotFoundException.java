package com.example.hra.exception;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String message)
    {
        super(message);
    }
}
