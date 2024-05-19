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
import java.util.HashMap;

@Component
@Slf4j
public class RequestGlobalFilter implements GlobalFilter {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        boolean vip = request.getURI().getPath().contains("vip");
        boolean ico = request.getURI().getPath().contains("ico");
        log.info("请求路径为{}",request.getURI().getPath());
        //如果是vip就不用进行任何校验。
        if (vip){
            log.info("vip用户");
          return   chain.filter(exchange);
        }if (ico){
            return chain.filter(exchange);
        }
        HttpHeaders headers = request.getHeaders();
        String publicKey = headers.getFirst("OpenApi-Public-Key");
        String signature = headers.getFirst("OpenApi-Signature");
        String email = headers.getFirst("email");
        //根据公钥从redis中取出用户数据
        String EmailUser = stringRedisTemplate.opsForValue().get(publicKey);
        User user = JSONUtil.toBean(EmailUser, User.class);
                    String userPublicKey = user.getPublicKey();
                    String userSign = user.getSign();
                    String privateKey = user.getPrivateKey();
                    System.out.println("数据库用户的邮箱是"+email);
                    if (publicKey.equals(userPublicKey) && signature.equals(userSign)) {
                        //拿到唯一公钥和唯一邮箱加上签名通过服务端保存的私钥进行解签，公钥和邮箱都不能够错误。
                        //这里的公钥必须传过来，因为要跟服务端的私钥生成签名对象。
                        Sign pen = SignUtil.sign(SignAlgorithm.SHA256withRSA, privateKey, publicKey);
                        //base64转byte[]
                        byte[] Signature = Base64.getDecoder().decode(signature);
                        //通过签名和邮箱解签
                        boolean verify = pen.verify(email.getBytes(), Signature);
                        //验证成功
                        if (verify) {
                            //通过
                           return chain.filter(exchange);
                        } else {
                            throw new RuntimeException("1");
                        }
                    } else {
                        throw new RuntimeException("用户不匹配");
                    }

    }


}

