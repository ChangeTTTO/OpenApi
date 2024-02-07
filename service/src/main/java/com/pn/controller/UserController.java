package com.pn.controller;

import com.pn.common.Result;
import com.pn.domain.dto.UserLoginDto;
import com.pn.domain.vo.userLoginVo;
import com.pn.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/login")
    @Operation(summary = "")
    public Result login(@RequestBody UserLoginDto user){
        if (user==null){
            return Result.error("参数错误");
        }
        if (user.getEmail().length()<2 || user.getPassword().length()<2){
            return Result.error("数据格式不正确");
        }
        userLoginVo dbUser = userService.getUser(user.getEmail());
        if (dbUser==null){
            return  Result.error("用户不存在");
        }
       //校验完成，登陆成功
        return Result.success(dbUser);
    }
}
