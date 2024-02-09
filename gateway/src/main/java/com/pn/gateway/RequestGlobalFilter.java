package com.pn.gateway;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.pn.client.serviceClient.serviceClient;
import com.pn.domain.vo.userLoginVo;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestGlobalFilter implements GlobalFilter, Ordered {
    @Resource
    @Lazy
    private serviceClient serviceClient;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        RSA rsa = new RSA();
        //1.从请求头里获取publicKey和sign
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String publicKey = headers.getFirst("OpenApi-Public-Key");
        String signature = headers.getFirst("OpenApi-Signature");
        //2.获取到登录用户的邮箱
        String email = (String) StpUtil.getLoginId();
        //3.通过远程调用获取用户信息
        userLoginVo userByEmail = serviceClient.findUserByEmail(email);
        String userPublicKey = userByEmail.getPublicKey();
        String userSign = userByEmail.getSign();
        //4.验证公钥的一致性：确保请求头中携带的公钥与数据库中存储的用户公钥一致。
        if (publicKey.equals(userPublicKey)&&signature.equals(userSign)){
            //5.使用公钥对签名进行验证
            String decrypted = rsa.decryptStr(signature, KeyType.valueOf(publicKey));
            //6.验证成功
            if (decrypted.equals(email)){
                    log.info("验证成功");
            }else {
                log.info("验证失败");
            }
        }

        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
