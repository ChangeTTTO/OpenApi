package com.pn.feign.client;
import com.pn.api.common.Result;
import com.pn.api.domain.dto.EnglishParams;
import com.pn.api.domain.dto.WeatherParams;
import com.pn.api.domain.dto.invokeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api",contextId = "2")
public interface ApiClient {
    @PostMapping("/name")
    String getName(@RequestBody Object name);
    @GetMapping("/loveTalk")
    Result randomLoveTalk();
    @GetMapping("/poisonousChickenSoup")
    public String getPoisonousChickenSoup();
    @GetMapping("/randomWallpaper")
    public String getRandomWallpaper();
    @GetMapping("/english")
    public String getEnglishInfo();
}
