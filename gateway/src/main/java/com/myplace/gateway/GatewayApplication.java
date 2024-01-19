package com.myplace.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}

/*

          api-client-oidc:
            provider: spring
            client-id: api-client
            client-secret: secret
            client-authentication-method: client-secret-basic
            authorization-grant-type: authorization_code
            redirect-uri:
              - "http://localhost:8080/login/oauth2/code/{registrationId}"
              - "http://localhost:8080/authorized"
            scope:
              - openid
              - api.read
            client-name: api-client-oidc
            provider:
              spring:
                issuer-uri: http://localhost:9001
 */
