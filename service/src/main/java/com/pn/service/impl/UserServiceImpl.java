package com.pn.service.impl;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SignUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import com.pn.domain.vo.userLoginVo;
import com.pn.feign.util.RsaUtil;
import com.pn.mapper.UserMapper;
import com.pn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        RSA rsa = RsaUtil.getRsa();
        //获得私钥
        String privateKey = rsa.getPrivateKeyBase64();
        //获得公钥
        String publicKey = rsa.getPublicKeyBase64();
        Sign pen = SignUtil.sign(SignAlgorithm.SHA256withRSA, privateKey, publicKey);
        //对邮箱名进行签名
        byte[] sign = pen.sign(email);
        //转为base64写入数据库
        String signedBy64 = Base64.getEncoder().encodeToString(sign);
        //写入数据
        userMapper.register(email,password,publicKey,privateKey, signedBy64);
    }

    @Override
    public userLoginVo findUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }
}





