package com.pn.api.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.pn.api.common.Result;
import com.pn.api.domain.dto.HoroscopeParams;
import com.pn.api.domain.dto.IpInfoParams;
import com.pn.api.domain.dto.WeatherParams;
import com.pn.api.utils.RequestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Target;

@RestController
@CrossOrigin
@RequestMapping("/vip")

public class vipController {
    /**
     * 获取随机毒鸡汤
     * 完成√
     */
    @GetMapping("/poisonousChickenSoup")
    public Result getPoisonousChickenSoup() {
        return  Result.success(HttpRequest.get("https://api.btstu.cn/yan/api.php").execute().body());
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
    @GetMapping("/getIpInfo")
    public String getIpInfo(IpInfoParams ipInfoParams) {
        return RequestUtils.get("https://api.vvhan.com/api/getIpInfo", ipInfoParams);
    }

    @GetMapping("/weather")
    public String getWeatherInfo(WeatherParams weatherParams) {
        return RequestUtils.get("https://api.vvhan.com/api/weather", weatherParams);
    }
    @GetMapping("/getMovies")
    public String getMovies()  {
        return RequestUtils.get("https://api.vvhan.com/api/movies");
    }
    @GetMapping("/getVisitorInfo")
    public String getVisitorInfo()  {
        return RequestUtils.get("https://api.vvhan.com/api/visitor.info");
    }
}
