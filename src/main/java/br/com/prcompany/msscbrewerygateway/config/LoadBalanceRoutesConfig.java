package br.com.prcompany.msscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class LoadBalanceRoutesConfig {

    @Bean
    public RouteLocator loadBalanceRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("beer-service-id", r -> r.path("/api/v1/beer/*", "/api/v1/beer*", "/api/v1/beerUpc/*").uri("lb://beer-service"))
                .route("beer-order-service-id", r -> r.path("/api/v1/customers*", "/api/v1/customers/*").uri("lb://beer-order-service"))
                .route("beer-inventory-service-id", r -> r.path("/api/v1/beer/*/inventory").uri("lb://beer-inventory-service"))
                .build();

//        return builder.routes()
//                .route("beer-service", r -> r.path("/api/v1/beer/*", "/api/v1/beer*", "/api/v1/beerUpc/*").uri("http://localhost:8080"))
//                .route("beer-order-service", r -> r.path("/api/v1/customers*", "/api/v1/customers/*").uri("http://localhost:8081"))
//                .route("beer-inventory-service", r -> r.path("/api/v1/beer/*/inventory").uri("http://localhost:8082"))
//                .build();
    }
}
