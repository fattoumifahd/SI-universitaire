package com.example.gatway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class GlobalLogFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url =  exchange.getRequest().getURI().getPath();
        System.out.println("GatwayGatewayFilter filter chain : url ="+url);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
