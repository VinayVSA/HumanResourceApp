package com.example.hra.exceptions;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String message)
    {
        super(message);
    }
}
