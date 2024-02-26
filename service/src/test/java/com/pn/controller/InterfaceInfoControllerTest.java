package com.pn.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SignUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.http.HttpRequest;
import com.pn.feign.util.RsaUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Base64;

public class InterfaceInfoControllerTest {

    @Test
    public void invokeInterface() {
       /* RSA rsa = RsaUtil.getRsa();
        //获得私钥
        String privateKey = rsa.getPrivateKeyBase64();
        //获得公钥
        String publicKey = rsa.getPublicKeyBase64();
        Sign pen = SignUtil.sign(SignAlgorithm.SHA256withRSA, privateKey, publicKey);
        //对邮箱名进行签名
      *//*  String sign = signed.signHex("email");*//*
        String email = "email";
        byte[] signed = pen.sign(email);
        String signedBy64 = Base64.getEncoder().encodeToString(signed);
        byte[] decodedBytes = Base64.getDecoder().decode(signedBy64);
        System.out.println(signedBy64);
        Sign pen2 = SignUtil.sign(SignAlgorithm.SHA256withRSA,privateKey,publicKey);
        boolean verify = pen2.verify("email".getBytes(), decodedBytes);
        System.out.println(verify);*/
        String body = HttpRequest.get("https://api.vvhan.com/api/love").execute().body();
        System.out.println(body);
    }
}
