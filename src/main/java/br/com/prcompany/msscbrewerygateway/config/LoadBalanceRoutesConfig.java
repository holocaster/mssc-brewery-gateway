package br.com.prcompany.msscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalanceRoutesConfig {

    @Bean
    public RouteLocator loadBalanceRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("beer-service", r -> r.path("/api/v1/beer/*", "/api/v1/beer*", "/api/v1/beerUpc/*").uri("lb://beer-service"))
                .route("beer-order-service", r -> r.path("/api/v1/customers*", "/api/v1/customers/*").uri("lb://beer-order-service"))
                .route("beer-inventory-service", r -> r.path("/api/v1/beer/*/inventory")
                        .filters(f -> f.circuitBreaker(c -> c.setName("inventoryCB").setFallbackUri("forward:/inventory-failover").setRouteId("inventory-failover")))
                        .uri("lb://beer-inventory-service"))
                .route("inventory-failover", r -> r.path("/inventory-failover/**").uri("lb://inventory-failover"))
                .build();

//        return builder.routes()
//                .route("beer-service", r -> r.path("/api/v1/beer/*", "/api/v1/beer*", "/api/v1/beerUpc/*").uri("http://localhost:8080"))
//                .route("beer-order-service", r -> r.path("/api/v1/customers*", "/api/v1/customers/*").uri("http://localhost:8081"))
//                .route("beer-inventory-service", r -> r.path("/api/v1/beer/*/inventory").uri("http://localhost:8082"))
//                .build();
    }
}
