package com.pn.feign.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api",contextId = "2")
public interface ApiClient {
    @PostMapping("/")
    String getName(@RequestBody Object name);
    @PostMapping("/dog/keji")
     String getKeji();
}
