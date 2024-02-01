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
        return Result.success("登陆成功");
    }
}
