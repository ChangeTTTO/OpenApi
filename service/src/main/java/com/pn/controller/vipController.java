package com.pn.controller;

import cn.hutool.json.JSONUtil;
import com.pn.R;
import com.pn.RequestUtils;
import com.pn.domain.dto.HoroscopeParams;
import com.pn.domain.dto.IpInfoParams;
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
