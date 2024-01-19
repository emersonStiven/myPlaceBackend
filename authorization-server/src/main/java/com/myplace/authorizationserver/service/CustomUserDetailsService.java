package com.myplace.authorizationserver.service;


import com.myplace.authorizationserver.entity.Role;
import com.myplace.authorizationserver.entity.UserCredentials;
import com.myplace.authorizationserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /*
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

     */

    //@Bean
    //public PasswordEncoder passwordEncoder() {
       // return new BCryptPasswordEncoder(11);
    //}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserCredentials userCredentials = userRepository.findByEmail(email);
        if(userCredentials == null) {
            throw  new UsernameNotFoundException("No User Found");
        }
        return new org.springframework.security.core.userdetails.User(
                userCredentials.getEmail(),
                userCredentials.getPassword(),
                true,
                true,
                true,
                true,
                getAuthorities(userCredentials.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        List<GrantedAuthority>  authorities = new ArrayList<>();
        for(Role role: roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        }
        return authorities;
    }
}
