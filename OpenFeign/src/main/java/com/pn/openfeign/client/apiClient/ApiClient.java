package com.pn.openfeign.client.apiClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api")
public interface ApiClient {
    @GetMapping("/")
    String getName(@RequestParam String name);
}
