package com.emerson.propertyservice.ErrorHandling;

public class ListingDoesNotExistException extends Exception {
    public ListingDoesNotExistException(String message){
        super(message);
    }
}
