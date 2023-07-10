package com.example.hra.exceptions;

public class RegionNotFoundException extends RuntimeException{

    public RegionNotFoundException(String message)
    {
        super(message);
    }
}
