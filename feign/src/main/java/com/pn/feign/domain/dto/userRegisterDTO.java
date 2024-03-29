package com.pn.feign.domain.dto;

import lombok.Data;

@Data
public class userRegisterDTO {
    /**
     * 账号
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 私钥
     */
    private String privateKey;
}
