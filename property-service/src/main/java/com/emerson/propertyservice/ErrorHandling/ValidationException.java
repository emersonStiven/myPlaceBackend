package com.emerson.propertyservice.ErrorHandling;

import org.springframework.validation.BindingResult;

public class ValidationException extends RuntimeException {
    private BindingResult result;

    public ValidationException(String message, BindingResult result){
        super(message);
        this.result = result;
    }
    public BindingResult getBindingResult(){
        return this.result;
    }
}
