package com.pn.service.impl;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.pn.common.RSAUtil;
import com.pn.domain.vo.userLoginVo;
import com.pn.mapper.UserMapper;
import com.pn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        //获得私钥
        String privateKey = rsa.getPrivateKeyBase64();
        //获得公钥
        String publicKey = rsa.getPublicKeyBase64();
        //用私钥对邮箱进行签名
        String sign = rsa.encryptHex(email, CharsetUtil.CHARSET_UTF_8, KeyType.valueOf(privateKey));
        //写入数据
        userMapper.register(email,password,publicKey,privateKey,sign);
    }

    @Override
    public userLoginVo findUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }
}





