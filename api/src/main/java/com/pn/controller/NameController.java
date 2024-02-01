package com.pn.controller;

import com.pn.common.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/")
public class NameController {
    @GetMapping
    public Result getName(String name){
        return Result.success(name);
    }
}
