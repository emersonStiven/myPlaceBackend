package com.emerson.propertyservice.Validations;

import com.emerson.propertyservice.models.ListingDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class ListingManagementApiValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz){
        return (ListingDTO.class.isAssignableFrom(clazz)  );
    }

    @Override
    public void validate(Object target, Errors errors){

    }
}
