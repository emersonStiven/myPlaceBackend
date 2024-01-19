package com.myplace.usermanagement.validateData;

import com.myplace.usermanagement.models.MemberProfileDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProfileManagementValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz){
        return (MemberProfileDTO.class.isAssignableFrom(clazz) ||
                MemberProfileDTO.class.isAssignableFrom(clazz));
    }
    @Override
    public void validate(Object target, Errors errors) {
        if(target instanceof MemberProfileDTO){
            RegisterUser.validate(target, errors);
        }else if(target instanceof MemberProfileDTO){
            UpdateAccount.validate(target, errors);
        }
    }

}
