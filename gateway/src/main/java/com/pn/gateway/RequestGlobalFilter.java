package com.pn.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RequestGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.从请求头里获取publicKey和sign
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        // 获取OpenApi-Public-Key的值
        String publicKey = headers.getFirst("OpenApi-Public-Key");
        // 获取OpenApi-Signature的值
        String signature = headers.getFirst("OpenApi-Signature");
        //2.验证签名
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        queryParams.getFirst("");
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
