package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/first-service/**") // 요청이 오는 url 확인
//                        .filters(f -> f.addRequestHeader("first-request", "first-request-header") //헤더 설정
//                                .addResponseHeader("first-response", "first-response-header")) //응답 설정
//                        .uri("http://localhost:8081")) // mapping url
//                .route(r -> r.path("/second-service/**")
//                        .filters(f -> f.addRequestHeader("second-request", "second-request-header")
//                                .addResponseHeader("second-response", "second-response-header"))
//                        .uri("http://localhost:8082"))
//                .build();
//    }
}
