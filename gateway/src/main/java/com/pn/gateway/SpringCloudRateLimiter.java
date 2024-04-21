package com.pn.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
@Slf4j
public class SpringCloudRateLimiter {
    /**
     * 用户id限流
     */
    @Bean
    KeyResolver PublicKeyResolver(){

        return  exchange ->
        {
            String publicKey = exchange.getRequest().getHeaders().getFirst("OpenApi-Public-Key");
            String email = exchange.getRequest().getHeaders().getFirst("email");
            if (email == null) {
                return Mono.just("default");
            }else
                return Mono.just(Objects.requireNonNull(publicKey));
        };

    }
}
