package com.admin.sbsbackend.exceptions;

public class BikeServiceNotFoundException extends RuntimeException{
    public BikeServiceNotFoundException(String message) {
        super(message);
    }
}
