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
    public String getName(@RequestBody Object nameParams) {
        return nameParams.toString();
    }

    /**
     * 获取随机土味情话
     * 免费接口
     */
    @GetMapping("/loveTalk")
    public Result randomLoveTalk() {
        String result = HttpRequest.get("https://api.vvhan.com/api/love").execute().body();

        return Result.success(result);
    }

    /**
     * 获取必应每日图片
     * 免费接口
     */
    @GetMapping("/randomWallpaper")
    public String getRandomWallpaper() {
        return RequestUtils.get("https://api.vvhan.com/api/bing?type=json&rand=sj");
    }
    /**
     * 随机获取一句励志英文句子
     * 免费接口
     */
    @GetMapping("/english")
    public String getEnglishInfo() {
        return RequestUtils.get("https://api.vvhan.com/api/en?type=sj");
    }




}
