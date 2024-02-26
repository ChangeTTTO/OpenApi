package com.pn.domain.vo;

import lombok.Data;

@Data
public class InterfaceInfo {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 描述
     */
    private String description;
    /**
     * 接口名
     */
    private String name;
    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 请求示例
     */
    private String requestExample;

    /**
     * 接口状态（0-关闭，1-开启）
     */
    private Integer status;

    /**
     * 请求类型
     */
    private String method;


}
