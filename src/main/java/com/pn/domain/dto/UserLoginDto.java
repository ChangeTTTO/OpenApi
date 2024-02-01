package com.pn.domain.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    /**
     * 账号
     */
    private String userAccount;
    /**
     * 密码
     */
    private String userPassword;
}
