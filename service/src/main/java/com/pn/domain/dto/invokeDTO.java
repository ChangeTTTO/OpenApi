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
    private String methodName;
    /**
     * 需要调用的方法的参数
     */
    private Object params;
}
