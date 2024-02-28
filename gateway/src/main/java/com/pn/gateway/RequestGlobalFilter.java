package com.pn.gateway;
import cn.hutool.crypto.SignUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
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
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class RequestGlobalFilter implements GlobalFilter {
    @Resource
    private RabbitTemplate rabbitTemplate;
    private final CompletableFuture<User> future = new CompletableFuture<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.error("进入全局拦截器");
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String publicKey = headers.getFirst("OpenApi-Public-Key");
        String signature = headers.getFirst("OpenApi-Signature");
        //1.登录用户身份验证
        return Mono.fromRunnable(() -> {
            rabbitTemplate.convertAndSend("amq.direct", "findUser", publicKey);
            future.thenAccept(user -> {
                try {
                    String userPublicKey = user.getPublicKey();
                    String userSign = user.getSign();
                    String email = user.getEmail();
                    String privateKey = user.getPrivateKey();
                    log.error(email);
                    if (publicKey.equals(userPublicKey) && signature.equals(userSign)) {
                        Sign pen = SignUtil.sign(SignAlgorithm.SHA256withRSA, privateKey, publicKey);
                        //base64转byte[]
                        byte[] decodedBytes = Base64.getDecoder().decode(signature);
                        boolean verify = pen.verify(email.getBytes(), decodedBytes);
                        //验证成功
                        if (verify) {
                            //通过
                            log.info("验证通过");
                            chain.filter(exchange);
                        } else {
                            throw new RuntimeException("数据签名验证失败");
                        }
                    } else {
                        throw new RuntimeException("数据不完整或已被篡改");
                    }
                } catch (Exception e) {
                    log.error("身份验证异常", e);
                    throw new RuntimeException("身份验证异常", e);
                }
            }).exceptionally(throwable -> {
                log.error("身份验证异常", throwable);
                throw new RuntimeException("身份验证异常", throwable);
            }).join(); //等待线程结束
        }).then(chain.filter(exchange));
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "gateway"),
            exchange = @Exchange(name = "amq.direct", type = "direct"),
            key = {"getUser"}
    ))
    public void getUser(User user) {
        try {
            future.complete(user);
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}

