package com.pn.controller;
import cn.hutool.http.HttpRequest;
import com.pn.R;
import com.pn.RequestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ServiceController {

    /**
     * 获取随机土味情话
     * 免费接口
     */
    @GetMapping("/loveTalk")
    public R randomLoveTalk() {
        String result = HttpRequest.get("https://api.vvhan.com/api/love").execute().body();

        return R.success(result);
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
