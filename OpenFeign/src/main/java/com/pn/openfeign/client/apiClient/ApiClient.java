package com.pn.openfeign.client.apiClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api")
public interface ApiClient {
    @GetMapping("/")
    String getName(@RequestBody String name);
}
