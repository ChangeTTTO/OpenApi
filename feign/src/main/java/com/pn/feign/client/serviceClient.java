package com.pn.feign.client;


import com.pn.feign.domain.vo.userLoginVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "service",contextId = "1")
public interface serviceClient {
    @GetMapping("/user/findUserByEmail")
    userLoginVo findUserByEmail(String email);
    @GetMapping("/findUserByPublicKey")
    userLoginVo findUserByPublicKey(String publicKey);
}
