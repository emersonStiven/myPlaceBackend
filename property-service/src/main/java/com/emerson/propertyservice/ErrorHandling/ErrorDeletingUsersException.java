package com.emerson.propertyservice.ErrorHandling;

public class ErrorDeletingUsersException extends Exception {
    public ErrorDeletingUsersException(String message){
        super(message);
    }
}
