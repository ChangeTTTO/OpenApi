package com.pn.controller;

import com.pn.common.Result;
import com.pn.domain.dto.UserLoginDto;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RequestMapping("/user")
@RestController
public class UserController {
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto user){
        if (user==null){
            return Result.error("参数错误");
        }
        if (user.getUserAccount().length()<2 || user.getPassword().length()<2){
            return Result.error("数据格式不正确");
        }
        return Result.success(user);
    }
}
