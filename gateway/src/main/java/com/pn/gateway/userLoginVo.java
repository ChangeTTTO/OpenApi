package com.pn.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userLoginVo {
    /**
     * 用户id
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
     * 公钥
     */
    private String publicKey;
    /**
     * 签名
     */
    private  String sign;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
