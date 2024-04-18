package com.pn.gateway;
import cn.hutool.crypto.SignUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.Base64;

@Component
public class RequestGlobalFilter implements GlobalFilter {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String publicKey = headers.getFirst("OpenApi-Public-Key");
        String signature = headers.getFirst("OpenApi-Signature");
        String email = headers.getFirst("email");
        //根据邮箱从redis中取出用户数据
        String EmailUser = stringRedisTemplate.opsForValue().get(email);
        User user = JSONUtil.toBean(EmailUser, User.class);
                    String userPublicKey = user.getPublicKey();
                    String userSign = user.getSign();
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
                            chain.filter(exchange);
                        } else {
                            throw new RuntimeException("1");
                        }
                    } else {
                        throw new RuntimeException("用户不匹配");
                    }
        return chain.filter(exchange);
    }

}

