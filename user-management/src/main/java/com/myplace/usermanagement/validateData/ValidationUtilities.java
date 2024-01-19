package com.myplace.usermanagement.validateData;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.validation.Errors;

import java.util.Objects;
import java.util.regex.Pattern;

public class ValidationUtilities {
    private static Pattern p = Pattern.compile("^[\\S]\\w{5,50}$");
    private static Pattern n = Pattern.compile("^[a-zA-Z ]{1,50}$");
    public static void validName(String name, Errors errors) throws Exception {
        if(!Objects.nonNull(name)) throw new Exception();
        if(!n.matcher(name).matches()){
            errors.reject("100","Invalid naming format: "+ name);
            throw new Exception();
        }
    }

    public static void validEmail(String email, Errors errors) throws Exception {
        if(!Objects.nonNull(email))  throw new Exception();
        if (!(EmailValidator.getInstance().isValid(email))) {
            errors.reject("101", "Invalid email address: " + email);
            throw new Exception();
        }
    }

    public static void validPassword(String password, Errors errors) throws Exception{
        if(!Objects.nonNull(password))  throw new Exception();
        if (!p.matcher(password).matches()) {
            errors.reject("102","Invalid password format");
            throw new Exception();
        }
    }
}
