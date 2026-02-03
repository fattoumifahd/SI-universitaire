package com.example.gatway.config;

import com.example.gatway.filter.CustomerFilter;
import com.example.gatway.filter.GlobalLogFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Component
public class GatwayRoutesConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("studentms", r -> r
                        .path("/studentms/**")
                        .filters(f -> f
                                .rewritePath("/studentms/(?<remaining>.*)", "/${remaining}")
                                .addRequestHeader("X-Request-Origin", "Gatway")
                                .filter(new CustomerFilter())
                        )
                        .uri("lb://studentms")
                )
                .route("coursms", r -> r
                        .path("/coursms/**")
                        .filters(f -> f
                                .rewritePath("/coursms/(?<remaining>.*)", "/${remaining}")
                                .addRequestHeader("X-Request-Origin", "Getway")
                                .filter(new CustomerFilter())
                        )
                        .uri("lb://coursms")
                )
                .build();
    }


}
