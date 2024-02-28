package com.pn.feign.client;
import com.pn.api.common.Result;
import com.pn.api.domain.dto.EnglishParams;
import com.pn.api.domain.dto.WeatherParams;
import com.pn.api.domain.dto.invokeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "api",contextId = "2")
public interface ApiClient {
    @PostMapping("/api/name")
    String getName(@RequestBody Object name);
    @GetMapping("/api/loveTalk")
    Result randomLoveTalk();
    @GetMapping("/api/poisonousChickenSoup")
    public String getPoisonousChickenSoup();
    @GetMapping("/api/randomWallpaper")
    public String getRandomWallpaper();
    @GetMapping("/api/english")
    public String getEnglishInfo();
}
