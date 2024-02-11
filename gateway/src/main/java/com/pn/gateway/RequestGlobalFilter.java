package com.pn.gateway;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class RequestGlobalFilter implements GlobalFilter {
    @Resource
    private RabbitTemplate rabbitTemplate;
    private final CompletableFuture<userLoginVo> future =new CompletableFuture<>();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.error("进入网关");
        System.out.println("成功进入");
        RSA rsa = new RSA();
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String publicKey = headers.getFirst("OpenApi-Public-Key");
        String signature = headers.getFirst("OpenApi-Signature");


        rabbitTemplate.convertAndSend("amq.direct", "findUser", publicKey);

        future.thenAccept(user -> {
            String userPublicKey = user.getPublicKey();
            String userSign = user.getSign();
            String email = user.getEmail();
            log.error(email);
            if (publicKey.equals(userPublicKey) && signature.equals(userSign)) {
                String decrypted = rsa.decryptStr(signature, KeyType.valueOf(publicKey));
                if (decrypted.equals(email)) {
                    log.info("验证成功");
                } else {
                    log.info("验证失败");
                }
            }
        });

        return chain.filter(exchange);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "gateway"),
            exchange = @Exchange(name = "amq.direct", type = "direct"),
            key = {"getUser"}
    ))
    public void getUser(userLoginVo user) {
        future.complete(user);
    }
}
