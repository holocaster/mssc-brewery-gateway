package br.com.prcompany.msscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local-discovery")
@Configuration
public class LocalhostRouteConfig {

    @Bean
    public RouteLocator localhostRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("beer-service", r -> r.path("/api/v1/beer/*", "/api/v1/beer*", "/api/v1/beerUpc/*").uri("http://localhost:8080"))
                .route("beer-order-service", r -> r.path("/api/v1/customers*", "/api/v1/customers/*").uri("http://localhost:8081"))
                .route("beer-inventory-service", r -> r.path("/api/v1/beer/*/inventory").uri("http://localhost:8082"))
                .build();
    }
}
