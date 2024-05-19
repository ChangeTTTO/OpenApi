package com.pn.gateway;

import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Configuration
public class GatewayConfig {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Bean
    public GlobalFilter customFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // 请求结束后的操作
                String requestPath = exchange.getRequest().getPath().toString();
                System.out.println("请求 " + requestPath + " 已结束");
                // 在这里可以执行其他操作，例如记录日志、触发事件等
                ServerHttpRequest request = exchange.getRequest();
                HttpHeaders headers = request.getHeaders();
                String key = headers.getFirst("OpenApi-Public-Key");
                // 设置超时时间
                stringRedisTemplate.expire(key, 30, TimeUnit.MINUTES);
            }));
        };
    }
}