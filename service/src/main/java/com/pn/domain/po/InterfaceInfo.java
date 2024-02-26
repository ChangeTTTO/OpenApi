package com.pn.domain.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 接口信息
 * </p>
 *
 * @author pn
 * @since 2024-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InterfaceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口id
     */
    private Long id;

    /**
     * 接口名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

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
     * 接口细节描述
     */
    private String detail;
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

    /**
     * 创建人
     */
    private Long userId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除(0-未删, 1-已删)
     */
    private Integer isDelete;


}
