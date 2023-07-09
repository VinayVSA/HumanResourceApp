package com.example.hra.exception;

public class ValidationFailedException extends RuntimeException{
    public ValidationFailedException(String message)
    {
        super(message);
    }
}
