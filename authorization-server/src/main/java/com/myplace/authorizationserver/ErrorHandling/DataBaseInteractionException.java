package com.myplace.authorizationserver.ErrorHandling;

public class DataBaseInteractionException extends RuntimeException{
    boolean ok;
    public DataBaseInteractionException(String message){
        super(message);
        this.ok = false;

    }
    public boolean getOk(){
        return this.ok;
    }
}
