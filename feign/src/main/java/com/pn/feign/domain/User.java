package com.pn.feign.domain;


import lombok.Data;

import java.io.Serializable;

/**
 * 用户
 * @TableName user
 */

@Data
public class User implements Serializable {


    /**
     * 邮箱账号
     */
    private String email;


    /**
     * 密码
     */
    private String userPassword;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 签名
     */
    private String sign;


}