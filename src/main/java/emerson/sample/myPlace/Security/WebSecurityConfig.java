package emerson.sample.myPlace.Security;

import emerson.sample.myPlace.Repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@ComponentScan(basePackages = "emerson.sample.myPlace")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private JwtAuthenticationFilter authenticationFilter;
    private JwtAuthorizationFilter authorizationFilter;
    private ManagerProvider managerProvider;
    private UsersRepository usersRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http
               .csrf(csrf -> csrf.disable())
               .cors(Customizer.withDefaults())
               .authorizeHttpRequests(a -> {
                   a.requestMatchers("/register").permitAll();
                    a.requestMatchers("/geographyInfo").permitAll();
                    a.requestMatchers("/validate-email").permitAll();
               })
               .sessionManagement(s ->{
                   s.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
               })
               .authenticationManager(managerProvider)

               .addFilter(authenticationFilter)
               .addFilterAfter(authorizationFilter, JwtAuthenticationFilter.class)
               .build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return (String e) -> usersRepository.findByEmail(e).get();
    }



}
