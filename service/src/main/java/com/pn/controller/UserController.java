package com.pn.controller;
import com.pn.feign.domain.User;
import com.pn.common.Result;
import com.pn.domain.dto.UserLoginDto;
import com.pn.domain.dto.userRegisterDTO;

import com.pn.domain.vo.userLoginVo;
import com.pn.mapper.UserMapper;
import com.pn.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@RequestMapping("/user")
@RestController
@Slf4j
@Tag(name = "用户接口")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @PostMapping("/login")
    @Operation(summary = "登录")
    public Result login(@RequestBody UserLoginDto user, HttpServletRequest request){
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
        request.getSession().setAttribute("user",user.getEmail());

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
            value = @Queue(name = "findUser",durable = "false"),
            exchange = @Exchange(name = "amq.direct",type ="direct"),
            key = {"findUser"}
    ))
    public void findUserByPublicKey(String publicKey){
        User user = userMapper.findUserByPublicKey(publicKey);
        if (user==null){
            throw new RuntimeException("用户不匹配");
        }
        rabbitTemplate.convertAndSend("amq.direct", "getUser", user);
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
    @GetMapping("/logout")
    @Operation(summary = "退出登录")
    Result logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return Result.success();
    }
}
