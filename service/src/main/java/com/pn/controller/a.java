package com.pn.controller;

import com.pn.openfeign.client.apiClient.ApiClient;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class a {
    @Resource
    private ApiClient client;
    @GetMapping("/")
    public String getName(String name){
        return client.getName(name);
    }
}
