package com.pn.service.impl;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SignUtil;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import com.pn.domain.vo.userLoginVo;
import com.pn.feign.util.RsaUtil;
import com.pn.mapper.UserMapper;
import com.pn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
* @author pn
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-02-02 05:45:17
*/
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    @Override
    public userLoginVo getUser(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public void register(String email, String password) {
        RSA rsa = new RSA();
        // 生成随机的密钥对
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey Private = pair.getPrivate();
        PublicKey Public = pair.getPublic();
        String privateKey = Base64.getEncoder().encodeToString(Private.getEncoded());
        String publicKey = Base64.getEncoder().encodeToString(Public.getEncoded());
        // 对邮箱名进行签名
        Sign pen = SignUtil.sign(SignAlgorithm.SHA256withRSA, privateKey, publicKey);
        byte[] sign = pen.sign(email);
        String signedBy64 = Base64.getEncoder().encodeToString(sign);
        // 写入数据库
        userMapper.register(email, password, publicKey, privateKey, signedBy64);
    }


    @Override
    public userLoginVo findUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public void setVip(String email) {
        userMapper.setVip(email);
    }
}





