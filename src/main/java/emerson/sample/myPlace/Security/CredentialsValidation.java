package emerson.sample.myPlace.Security;

import emerson.sample.myPlace.Entities.Users;
import java.util.function.BiFunction;


public  interface CredentialsValidation extends BiFunction<Users, AuthenticationToken, CredentialsValidation.Result> {

    static CredentialsValidation isEmailValid(){
        return (Users credential,AuthenticationToken authToken) ->
                credential.getEmail().equals(authToken.getAuthenticationRequest().email())
                        ? Result.SUCCESS : Result.EMAIL_NOT_VALID;
    }
    static CredentialsValidation isPasswordValid(){
        return (Users credential, AuthenticationToken authToken) ->
                credential.getPassword().equals(authToken.getAuthenticationRequest().password())? Result.SUCCESS:Result.PASSWORD_NOT_VALID;
    }
    default CredentialsValidation and(Users credential,AuthenticationToken authToken){
        return (Users c, AuthenticationToken t) ->{
                Result r = this.apply( c,  t);
                return r.equals(Result.SUCCESS) ? this.apply(credential, authToken) : r;
        };
    }

    enum Result {
        EMAIL_NOT_VALID, PASSWORD_NOT_VALID, SUCCESS
    }
}
