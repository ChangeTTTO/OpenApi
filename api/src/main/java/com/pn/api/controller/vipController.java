package com.pn.api.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.pn.api.R;
import com.pn.api.domain.dto.HoroscopeParams;
import com.pn.api.domain.dto.IpInfoParams;
import com.pn.api.domain.dto.WeatherParams;
import com.pn.api.utils.RequestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip")
public class vipController {
    /**
     * 获取星座运势
     */
    @GetMapping("/horoscope")
    public R getHoroscope(HoroscopeParams horoscopeParams)  {
        String response = RequestUtils.get("https://api.vvhan.com/api/horoscope", horoscopeParams);
        return JSONUtil.toBean(response, R.class);
    }
    /**
     * 获取ip信息归属地
     */
    @GetMapping("/getIpInfo")
    public String getIpInfo(IpInfoParams ipInfoParams) {
        return RequestUtils.get("https://api.vvhan.com/api/getIpInfo", ipInfoParams);
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
