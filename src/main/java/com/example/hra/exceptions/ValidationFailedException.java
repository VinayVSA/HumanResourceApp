package com.example.hra.exceptions;

public class ValidationFailedException extends RuntimeException{
    public ValidationFailedException(String message)
    {
        super(message);
    }
}
