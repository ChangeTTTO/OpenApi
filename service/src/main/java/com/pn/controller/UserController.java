package com.pn.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.pn.common.Result;
import com.pn.domain.dto.UserLoginDto;
import com.pn.domain.dto.userRegisterDTO;
import com.pn.domain.po.User;
import com.pn.domain.vo.userLoginVo;
import com.pn.mapper.UserMapper;
import com.pn.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@Slf4j
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;
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

        //校验完成，登陆成功,将session保存到redis
        StpUtil.login(user.getEmail());

        return Result.success(dbUser);
    }
    @PostMapping("/register")
    @Operation(summary = "注册")
    public Result register(@RequestBody userRegisterDTO userRegisterDTO){
        userLoginVo userByEmail = userService.findUserByEmail(userRegisterDTO.getEmail());
        if (userByEmail!=null){
            return Result.error("该邮箱已被使用");
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
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "gateway"),
            exchange = @Exchange(name = "amq.direct",type ="direct"),
            key = {"findUser"}
    ))
    public User findUserByPublicKey(String publicKey){
        User user = userMapper.findUserByPublicKey(publicKey);
        if (user==null){
            log.error("未查询到用户");
        }
        rabbitTemplate.convertAndSend("amq.direct", "getUser", user);
        return null;
    }
    @PostMapping("/vip")
    @Operation(summary = "根据用户邮箱vip充值")
    public Result setVip(@RequestBody UserLoginDto user){
        StpUtil.checkLogin();
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
}
