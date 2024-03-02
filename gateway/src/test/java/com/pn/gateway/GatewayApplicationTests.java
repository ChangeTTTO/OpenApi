package com.pn.gateway;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SignUtil;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@SpringBootTest
class GatewayApplicationTests {

    @Test
    void contextLoads() {
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey Private = pair.getPrivate();
        PublicKey Public = pair.getPublic();
        String privateKey = Base64.getEncoder().encodeToString(Private.getEncoded());
        String publicKey = Base64.getEncoder().encodeToString(Public.getEncoded());

        KeyPair b = SecureUtil.generateKeyPair("RSA");
        PrivateKey bb = b.getPrivate();
        PublicKey cc = b.getPublic();
        String bbb = Base64.getEncoder().encodeToString(bb.getEncoded());
        String ccc = Base64.getEncoder().encodeToString(cc.getEncoded());
        System.out.println(privateKey);
        System.out.println(publicKey);
        System.out.println(bbb);
        System.out.println(ccc);
    }

}
