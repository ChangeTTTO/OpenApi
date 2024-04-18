package com.pn.client;

import com.pn.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "service",contextId = "2")
public interface ApiClient {

    @GetMapping("/loveTalk")
    R randomLoveTalk();
    @GetMapping("/randomWallpaper")
     String getRandomWallpaper();
    @GetMapping("/english")
     String getEnglishInfo();
}
