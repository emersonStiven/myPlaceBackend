package com.emerson.propertyservice.ErrorHandling;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
