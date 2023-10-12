package emerson.sample.myPlace.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class ManagerProvider implements AuthenticationManager {
    //LIST OF AUTHEN PROVIDERS
    private EmailPasswordAuthenticationProvider authProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authProvider.supports(authentication.getClass())){
            return authProvider.authenticate(authentication);
        }else{
            throw new ProviderNotFoundException("Authentication token not supported");
        }

    }
}
