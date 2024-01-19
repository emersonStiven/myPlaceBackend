package com.myplace.authorizationserver.config;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
@Component
public class UrlValidatorProvider implements Consumer<OAuth2AuthorizationCodeRequestAuthenticationContext> {
    @Override
    public void accept(OAuth2AuthorizationCodeRequestAuthenticationContext context){
        //context.getAuthentication().getAuthorities(); explore what returns that obj

        //We get the token from the AuthenticationContext, like the SecurityContext
        OAuth2AuthorizationCodeRequestAuthenticationToken token = context.getAuthentication();

        //We get the registeredClient from the context as well
        RegisteredClient registeredClient = context.getRegisteredClient();

        //We get the redirectUri sent via the URL
        String uri = token.getRedirectUri();
        //THIS METHOD IS EXECUTED FIRST, BASICALLY, OAUTH SERVER VERIFIES THE CLIENT FIRST
        //THEN THE LOGIN PAGE IS SHOWN, AND THEN, THE USER GETS VERIFIED
        //SO THE SYSTEM UNDER THE HOOD TAKES AND FETCHES FOR THE CLIENT IN THE /oauth/authorize endpoint
        System.out.println("URI: " + uri);
        registeredClient.getRedirectUris().forEach(t -> System.out.println(t));
        if(!registeredClient.getRedirectUris().contains(uri)){
            System.out.println("ERROROROROROR");
            var error = new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST);
            throw new OAuth2AuthorizationCodeRequestAuthenticationException(error,null);
        }
    }



}
