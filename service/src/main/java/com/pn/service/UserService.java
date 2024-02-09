package com.pn.service;

import com.pn.domain.dto.UserLoginDto;
import com.pn.domain.po.User;
import com.pn.domain.vo.userLoginVo;

/**
* @author pn
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-02-02 05:45:17
*/
public interface UserService{
    /**
     * 根据邮箱查询用户
     * @param email
     * @return User
     */
    userLoginVo getUser(String email);

    void register(String email, String password);

    userLoginVo findUserByEmail(String email);
}
