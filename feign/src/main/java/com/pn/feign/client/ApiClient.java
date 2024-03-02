package com.pn.feign.client;
import com.pn.feign.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "api",contextId = "2")
public interface ApiClient {
    @PostMapping("/api/name")
    String getName(@RequestBody Object name);
    @GetMapping("/api/loveTalk")
    R randomLoveTalk();
    @GetMapping("/api/poisonousChickenSoup")
     String getPoisonousChickenSoup();
    @GetMapping("/api/randomWallpaper")
     String getRandomWallpaper();
    @GetMapping("/api/english")
     String getEnglishInfo();
}
