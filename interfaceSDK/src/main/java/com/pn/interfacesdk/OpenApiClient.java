package com.pn.interfacesdk;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import org.junit.Test;

import java.util.HashMap;

public class OpenApiClient {
    @Test
    public void getName() {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "陶纪禄");
        String result = HttpUtil.get("http://localhost:8080/getName", paramMap);
        System.out.println(result);

    }
}
