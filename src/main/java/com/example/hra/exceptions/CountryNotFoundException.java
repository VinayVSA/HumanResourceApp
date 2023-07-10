package com.example.hra.exceptions;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(String message)
    {
        super(message);
    }

}
