package com.myplace.usermanagement.validateData;

import com.myplace.usermanagement.models.MemberAccountDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
@Service
public class RegisterUser  {
    public static void validate(Object target, Errors errors){
        MemberAccountDTO newUser = (MemberAccountDTO) target;
        try{
            ValidationUtilities.validName(newUser.firstName(), errors);
            ValidationUtilities.validName(newUser.lastName(), errors);
            ValidationUtilities.validEmail(newUser.email(), errors);
            ValidationUtilities.validPassword(newUser.password(), errors);
        }catch(Exception e){
            System.out.println("ERROROROROROROR");
        }
    }
}
