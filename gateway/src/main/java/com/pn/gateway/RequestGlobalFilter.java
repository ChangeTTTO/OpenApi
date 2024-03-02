package com.pn.gateway;
import cn.hutool.crypto.SignUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import com.pn.feign.domain.User;
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
                    System.out.println("数据库用户的邮箱是"+email);
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
                            throw new RuntimeException("1");
                        }
                    } else {
                        throw new RuntimeException("用户不匹配");
                    }
                } catch (Exception e) {
                    throw new RuntimeException("3", e);
                }
            }).exceptionally(throwable -> {
                throw new RuntimeException("4", throwable);
            }).join(); //等待线程结束
        }).then(chain.filter(exchange));
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "getUser",durable = "false"),
            exchange = @Exchange(name = "amq.direct", type = "direct"),
            key = {"getUser"}
    ))
    public void getUser(User user) {
        try {
            //将user作为结果
            future.complete(user);
            System.out.println("得到的用户是:"+user.toString());
        } catch (Exception e) {
            throw new RuntimeException("5", e);
        }
    }
}

