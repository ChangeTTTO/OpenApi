package com.pn.mapper;

import com.pn.domain.po.User;
import com.pn.domain.vo.userLoginVo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 11473
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2024-02-02 05:45:17
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper {

    userLoginVo getUserByEmail(String email);
}




