package com.pn.domain.vo;

import lombok.Data;

@Data
public class InterfaceInfoVo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 描述
     */
    private String description;
    /**
     * 接口细节描述
     */
    private String detail;
    private String requestParams;
}
