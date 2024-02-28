package com.pn.gateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class SpringCloudRateLimiter {
    /**
     * 用户id限流
     */
    @Bean
    KeyResolver PublicKeyResolver(){
        return  exchange ->
                Mono.just(Objects.requireNonNull(exchange.getRequest().getHeaders().getFirst("OpenApi-Public-Key")));
    }
}
