package com.example.gatway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


public class CustomerFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Logique avant d'appeler le prochain filter appliquee avec secces");
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    System.out.println("GustomGateway filter chain : traitement apres la requets ");
                }));
    }
}
