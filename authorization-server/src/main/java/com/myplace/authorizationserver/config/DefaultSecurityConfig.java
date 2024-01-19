package com.myplace.authorizationserver.config;

import com.myplace.authorizationserver.service.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Collections;

@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> {
                            authorizeRequests.requestMatchers("/login").permitAll();
                            authorizeRequests.requestMatchers("/create-usercredentials").permitAll();
                            authorizeRequests.anyRequest().authenticated();
                        }
                )
                .cors(cors -> cors.configurationSource( corsConfigurationSource() ))
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        //boolean useAllowedOriginPatterns = allowedOrigins.isEmpty() || allowedOrigins.contains("*");
        boolean useAllowedOriginPatterns = true;

        if (useAllowedOriginPatterns) {
            configuration.setAllowedOriginPatterns(Collections.singletonList(CorsConfiguration.ALL));
        } else {
            configuration.setAllowedOrigins(new ArrayList<>(null));
        }
        configuration.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        configuration.setAllowedHeaders(Collections.singletonList(CorsConfiguration.ALL));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Autowired
    public void bindAuthenticationProvider(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder
                .authenticationProvider(customAuthenticationProvider);
    }
}
