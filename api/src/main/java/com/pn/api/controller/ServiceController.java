package com.pn.api.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import com.pn.api.common.Result;
import com.pn.api.domain.dto.*;
import com.pn.api.utils.RequestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ServiceController {
    @PostMapping("/name")
    public Result getName(@RequestBody Object nameParams) {
        return JSONUtil.toBean(JSONUtil.toJsonStr(nameParams), Result.class);
    }

    /**
     * 获取随机土味情话
     */
    @GetMapping("/loveTalk")
    public String randomLoveTalk() {
        return HttpRequest.get("https://api.vvhan.com/api/love").execute().body();
    }

    /**
     * 获取随机毒鸡汤
     */
    @GetMapping("/poisonousChickenSoup")
    public String getPoisonousChickenSoup() {
        return  HttpRequest.get("https://api.btstu.cn/yan/api.php?charset=utf-8&encode=json").execute().body();
    }
    /**
     * 获取随机手机壁纸
     */
    @GetMapping("/randomWallpaper")
    public String getRandomWallpaper(RandomWallpaperParams randomWallpaperParams) {
        return RequestUtils.get("https://api.btstu.cn/sjbz/api.php",randomWallpaperParams);

    }
    /**
     * 获取星座运势
     */
    @GetMapping("/horoscope")
    public Result getHoroscope( HoroscopeParams horoscopeParams)  {
        String response = RequestUtils.get("https://api.vvhan.com/api/horoscope", horoscopeParams);
        return JSONUtil.toBean(response, Result.class);
    }

    /**
     * 获取ip信息归属地
     */
    @GetMapping("/ipInfo")
    public String getIpInfo(IpInfoParams ipInfoParams) {
        return RequestUtils.get("https://api.vvhan.com/api/getIpInfo", ipInfoParams);
    }

    @GetMapping("/weather")
    public String getWeatherInfo(WeatherParams weatherParams) {
        return RequestUtils.get("https://api.vvhan.com/api/weather", weatherParams);
    }

    /**
     * 获取励志英文句子
     */
    @PostMapping("/english")
    public String getEnglishInfo( EnglishParams Params) {
        return RequestUtils.get("https://api.vvhan.com/api/en", Params);
    }

}
