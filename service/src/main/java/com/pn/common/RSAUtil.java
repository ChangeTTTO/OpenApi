package com.pn.common;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;


public class RSAUtil {
    RSA rsa = new RSA();
    //获得私钥
    String privateKey = rsa.getPrivateKeyBase64();
    //获得公钥
    String publicKey = rsa.getPublicKeyBase64();

}
