package com.myplace.usermanagement.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter jwtConverter = new JwtGrantedAuthoritiesConverter();
    //@Value ("${jwt.auth.converter.principal-attribute}")
    private String principalAttribute = "preferred_username";
    //@Value ("${jwt.auth.converter.resource-id}")
    private String resourceId ="myplace-client-api-rest";

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt){//we have to extract the roles from the token, so spring can recognize them when authorizing at the endppoint level
        Collection<GrantedAuthority> authorities = Stream.concat(jwtConverter.convert(jwt).stream() , extractRoles(jwt).stream() ).toList();
        JwtAuthenticationToken token = new JwtAuthenticationToken(jwt, authorities, getPrincipalName(jwt));
        return token;
    }

    private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt){
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> resourceRoles;
        if(jwt.getClaim("resource_access") == null){
            return Set.of();
        }
        resourceAccess = jwt.getClaim("resource_access");
        if(resourceAccess .get(resourceId) == null){
            return Set.of();
        }
        resource = (Map<String, Object>) resourceAccess.get(resourceId);

        if(resource.get("roles") == null){
            return Set.of();
        }

        resourceRoles = (Collection<String>) resource.get("roles");
        return resourceRoles.stream().map(r -> new SimpleGrantedAuthority("ROLE_"+r)).collect(Collectors.toSet());
    }

    private String getPrincipalName(Jwt jwt){
    // in case the principalAttribute is null, set the SUB as a backup
        String claimName = jwt.getSubject();
        //if(principalAttribute != null){
          //  claimName = principalAttribute;
        //}
        return claimName;
    }
}
