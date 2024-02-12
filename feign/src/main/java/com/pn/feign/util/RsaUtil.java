package com.pn.feign.util;

import cn.hutool.crypto.asymmetric.RSA;
import lombok.Getter;


@Getter
public class RsaUtil {
    private static RsaUtil instance;
    @Getter
    public static final RSA rsa =new RSA();
    private RsaUtil() {
    }
    public static synchronized RsaUtil getInstance() {
        // 使用懒汉式实例化，确保只有在需要时才创建实例
        if (instance == null) {
            instance = new RsaUtil();
        }
        return instance;
    }

}