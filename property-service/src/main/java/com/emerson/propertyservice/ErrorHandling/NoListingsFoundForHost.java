package com.emerson.propertyservice.ErrorHandling;

public class NoListingsFoundForHost extends Exception{
    public NoListingsFoundForHost(String host){
        super(host);
    }
}
