package com.pn.domain.dto;

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
}
