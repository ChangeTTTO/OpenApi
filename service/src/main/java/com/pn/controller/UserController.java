package com.pn.controller;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.pn.common.Result;
import com.pn.domain.dto.UserLoginDto;
import com.pn.domain.dto.userRegisterDTO;

import com.pn.domain.po.User;
import com.pn.domain.vo.userLoginVo;
import com.pn.mapper.UserMapper;
import com.pn.service.UserService;
import com.pn.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RequestMapping("/user")
@RestController
@Slf4j
@Tag(name = "用户接口")
public class UserController {
    @Resource
    private UserServiceImpl userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserMapper userMapper;

    @PostMapping("/login")
    @Operation(summary = "登录")
    public Result login(@RequestBody UserLoginDto user, HttpServletRequest request){
        String email = user.getEmail();
        if (user.getEmail().length()<2 || user.getPassword().length()<2){
            return Result.error("数据格式不正确");
        }
        userLoginVo dbUser = userService.getUser(user.getEmail());
        if (dbUser==null){
            return  Result.error("用户不存在");
        }

        String jsonStr = JSONUtil.toJsonStr(dbUser);
        //校验完成，登陆成功,将用户信息保存到redis
        stringRedisTemplate.opsForValue().set(email,jsonStr,30, TimeUnit.DAYS);

        return Result.success(dbUser);
    }
    @PostMapping("/register")
    @Operation(summary = "注册")
    public Result register(@RequestBody userRegisterDTO userRegisterDTO){
        userLoginVo userByEmail = userService.findUserByEmail(userRegisterDTO.getEmail());
        if (userByEmail!=null){
            return Result.error("该账号已存在");
        }
        userService.register(userRegisterDTO.getEmail(),userRegisterDTO.getPassword());
            return Result.success();
    }
    @GetMapping("/findUserByEmail")
    @Operation(summary = "根据邮箱查用户")
    public userLoginVo findUserByEmail( String email){
        return userService.findUserByEmail(email);
    }
    @GetMapping("/findUserByPublicKey")
    @Operation(summary = "根据公钥查用户")
    public void findUserByPublicKey(String publicKey){
        User user = userMapper.findUserByPublicKey(publicKey);
        if (user==null){
            throw new RuntimeException("用户不匹配");
        }
    }
    @PostMapping("/vip")
    @Operation(summary = "根据用户邮箱vip充值")
    public Result setVip(@RequestBody UserLoginDto user){
        userLoginVo dbUser = userService.findUserByEmail(user.getEmail());
        System.out.println(user.getEmail());
        System.out.println(dbUser.getEmail());
       /* if (!(user.getEmail().equals(dbUser.getEmail()))){
            return Result.error("错误");
        }*/
        //设置为vip用户
        userService.setVip(user.getEmail());
        return Result.success("成功");
    }
    @GetMapping("/logout/{email}")
    @Operation(summary = "退出登录")
    Result logout(@PathVariable String email){
        stringRedisTemplate.delete(email);
        return Result.success();
    }
}
