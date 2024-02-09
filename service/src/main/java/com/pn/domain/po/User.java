package com.pn.domain.po;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @TableName user
 */

@Data
public class User implements Serializable {
    /**
     * id
     */

    private Long id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 邮箱账号
     */
    private String email;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 用户角色：user / admin
     */
    private String userRole;

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
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;




}