package com.example.userservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4JConfig {
    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {

        // 서킷 브레이커의 설정을 커스터마이징 한다
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(4) // 실패율 임계값을 4%로 한다(100번 중 4번 실패시 오픈)
                .waitDurationInOpenState(Duration.ofMillis(1000)) // 서킷 브레이커가 열려있는 상태에서 대기시간을 1초로 한다.
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED) // 슬라이딩 윈도우 유형을 카운트 기반으로 한다.
                .slidingWindowSize(2) // 슬라이딩 윈도우의 크기를 2로 한다.
                // 슬라이딩 윈도우란 일정 시간 동안의 호출 정보를 저장하는 방식이다.
                .build();

        // 타임 리미트 설정을 커스터마이징 한다.
        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(4)) // 컨슈머 서비스의 타임아웃 시간(작업 완료 시간)을 4초로 한다.
                .build();

        // 서킷 브레이커 인스턴스에 대한 기본 설정을 적용하여 팩토리를 생성한다.
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(timeLimiterConfig)
                .circuitBreakerConfig(circuitBreakerConfig)
                .build());
    }
}
