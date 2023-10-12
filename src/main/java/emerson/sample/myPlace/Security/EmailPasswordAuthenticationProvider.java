package emerson.sample.myPlace.Security;

import emerson.sample.myPlace.Entities.Users;
import emerson.sample.myPlace.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class EmailPasswordAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UsersService usersService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticationToken authToken = (AuthenticationToken) authentication;
        Users users = usersService.findEmailAddress(authToken.getAuthenticationRequest().email())
                .orElseThrow(()-> new BadCredentialsException("The email " + authToken.getAuthenticationRequest().email() + " was not found"));

        String email = users.getEmail();
        String password = users.getPassword();

        //Validamos si las credenciales coinciden
        if(email.equals(authToken.getAuthenticationRequest().email()) &&
                password.equals(authToken.getAuthenticationRequest().password())){
            authToken.setAuthenticated(true);
             return authToken;
        }else{
            throw new BadCredentialsException("Credentials did not match");
        }
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(AuthenticationToken.class);
    }
}
