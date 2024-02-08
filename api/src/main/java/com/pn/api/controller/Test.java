package com.pn.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @PostMapping("/")
    public String getName(@RequestBody Object name){
        return name.toString();
    }
}
