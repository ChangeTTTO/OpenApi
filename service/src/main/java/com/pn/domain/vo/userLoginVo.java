package com.pn.domain.vo;

import lombok.Data;

import java.util.Date;
@Data
public class userLoginVo {
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
     * 公钥
     */
    private String publicKey;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
