package com.emerson.propertyservice.ErrorHandling;

public class ListingNotFoundException extends Exception{
    public ListingNotFoundException(String message){
        super(message);
    }
}
