package com.pn.service.impl;
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
}





