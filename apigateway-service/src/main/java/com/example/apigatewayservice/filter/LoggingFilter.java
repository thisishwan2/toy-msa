package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {


    public LoggingFilter(){
        super(LoggingFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            // Global Pre Filter
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
//
//            log.info("Global Filter baseMessage: {}", config.getBaseMessage());
//
//            if(config.isPreLogger()){
//                log.info("Global Filter Start: request id -> {}", request.getId());
//            }
//
//            // Global Post Filter
//            return chain.filter(exchange).then(Mono.fromRunnable(()-> {
//                if(config.isPostLogger()){
//                    log.info("Global Filter End: response code -> {}", response.getStatusCode());
//                }
//            }));
//        };

        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging Filter baseMessage: {}", config.getBaseMessage());

            if(config.isPreLogger()){
                log.info("Loggin Pre Filter: request id -> {}", request.getId());
            }

            // Global Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(()-> {
                if(config.isPostLogger()){
                    log.info("Loggin Post Filter: response code -> {}", response.getStatusCode());
                }
            }));
        }, Ordered.LOWEST_PRECEDENCE); // 우선순위에 따라 필터의 실행 순서가 바뀐다.

        return filter;
    }

    @Data
    public static class Config{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
