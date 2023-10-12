package emerson.sample.myPlace.ValidateData;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import emerson.sample.myPlace.DTOs.RequestCreateUser;

public class UserCreationValidator implements Validator {



    @Override
    public boolean supports(Class<?> clazz) {
        return RequestCreateUser.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        var obj = (RequestCreateUser) target;


    }

}
