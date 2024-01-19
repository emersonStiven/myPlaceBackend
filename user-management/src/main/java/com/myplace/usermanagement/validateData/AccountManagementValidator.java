package com.myplace.usermanagement.validateData;

import com.myplace.usermanagement.models.MemberAccountDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class AccountManagementValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz){
        return (MemberAccountDTO.class.isAssignableFrom(clazz) ||
                MemberAccountDTO.class.isAssignableFrom(clazz));
    }
    @Override
    public void validate(Object target, Errors errors) {
        if(target instanceof MemberAccountDTO){
            RegisterUser.validate(target, errors);
        }else if(target instanceof MemberAccountDTO){
            UpdateAccount.validate(target, errors);
        }
    }

}
