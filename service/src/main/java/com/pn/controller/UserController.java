package com.pn.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;
import com.pn.common.Result;
import com.pn.domain.dto.UserLoginDto;
import com.pn.domain.dto.userRegisterDTO;
import com.pn.domain.vo.userLoginVo;
import com.pn.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

@CrossOrigin("http://localhost:5173")
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/login")
    @Operation(summary = "登录")
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
    @PostMapping("/register")
    @Operation(summary = "注册")
    public Result register(@RequestBody userRegisterDTO userRegisterDTO){
        userLoginVo userByEmail = userService.findUserByEmail(userRegisterDTO.getEmail());
        if (userByEmail!=null){
            return Result.error("改邮箱已被使用");
        }
        userService.register(userRegisterDTO.getEmail(),userRegisterDTO.getPassword());
            return Result.success();
    }
    @GetMapping("/findUserByEmail")
    @Operation(summary = "根据邮箱查用户")
    public Result findUserByEmail(@RequestBody UserLoginDto user){
        return Result.success(userService.findUserByEmail(user.getEmail()));
    }
}
