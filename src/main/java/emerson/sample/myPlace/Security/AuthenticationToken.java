package emerson.sample.myPlace.Security;

import emerson.sample.myPlace.DTOs.AuthenticationRequest;
import emerson.sample.myPlace.Entities.Users;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.List;
@Data
@Component
@Builder
public class AuthenticationToken implements Authentication {
    private boolean  isAuthenticated = false;
    private Users user;
    private AuthenticationRequest authenticationRequest;
    private Object request;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return request;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }
    @Override
    public String getName() {
        return null;
    }
}
