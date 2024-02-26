package com.pn.domain.dto;

import lombok.Data;

/**
 * 方法调用时需要的参数
 */
@Data
public class invokeDTO {
    /**
     * 需要调用的方法名
     */
    private String interfaceName;
    /**
     * 需要调用的方法的参数
     */
    private Object requestParams;
    /**
     * 接口id
     */
    private String interfaceId;
    /**
     * 调用人id
     */
    private String userId;


}
