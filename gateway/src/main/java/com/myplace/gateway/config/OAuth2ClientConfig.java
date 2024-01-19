package com.myplace.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Configuration
@Controller
public class OAuth2ClientConfig {
    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(exchange -> exchange.matchers(EndpointRequest.toAnyEndpoint()).permitAll()
                        .anyExchange().authenticated())
                .oauth2Login(Customizer.withDefaults() )
                .oauth2Client(Customizer.withDefaults())
                .build();

    }
    @Bean
    public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(
            ReactiveClientRegistrationRepository clientRegistrationRepository,
            ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {

        ReactiveOAuth2AuthorizedClientProvider authorizedClientProvider =
                ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
                        .authorizationCode()
                        .refreshToken()
                        .build();

        DefaultReactiveOAuth2AuthorizedClientManager authorizedClientManager =
                new DefaultReactiveOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }
    /*
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes().route("resource",
                r -> r.path("/hello")
                .filters( f -> f.filter(filterFactory.apply()))
                .uri("http://localhost:8082"))
        }
     */

    @GetMapping("/")
    public String index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client, @AuthenticationPrincipal
    OAuth2User oAuth2User){
        return oAuth2User.toString() + "  " + client.toString();
    }



}
/*
 @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes().route("resource",
                r -> r.path("/hello")
                .filters( f -> f.filter(filterFactory.apply()))
                .uri("http://localhost:8082"))
                .route("preview-listings",
                        r -> r.path("/property-app/api/v1-crud/preview-listings")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://listings-service")) // assuming "listings-service" is your actual service name
                .route("fetch-listing", r ->
                        r.path("/property-app/api/v1-crud/retrieve-listing/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://listings-service"))
                .route("fetch-listing-by-host",
                        r -> r.path("/property-app/api/v1-crud/host-listings")
                                .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://listings-service"))
                .route("create-listing",
                        r -> r.path("/property-app/api/v1-crud/create-listing")
                                .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://listings-service"))
                .route("update-listing", r ->
                        r.path("/property-app/api/v1-crud/update-listing/**")
                                .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://listings-service"))
                .route("delete-listing-by-host",
                        r -> r.path("/property-app/api/v1-crud/delete-host-listings")
                                .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://listings-service"))
                .route("delete-listing-by-id",
                        r -> r.path("/property-app/api/v1-crud/delete-listing/**")
                                .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://listings-service"))
                .build();
    }
 */