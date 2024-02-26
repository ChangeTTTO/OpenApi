package com.pn.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 参数细节
 * </p>
 *
 * @author pn
 * @since 2024-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("params_details")
public class ParamsDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 参数名称
     */
    private String paramsName;

    /**
     * 是否必填
     */
    private String isRequest;

    /**
     * 参数类型
     */
    private String type;

    /**
     * 参数说明
     */
    private String description;

    /**
     * 接口id
     */
    private Long interfaceId;


}
