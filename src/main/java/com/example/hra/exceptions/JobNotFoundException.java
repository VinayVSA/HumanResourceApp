package com.example.hra.exceptions;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(String message)
    {
        super(message);
    }
}
