package com.pn.mapper;

import com.pn.common.Result;
import com.pn.domain.po.ParamsDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;

/**
 * <p>
 * 参数细节 Mapper 接口
 * </p>
 *
 * @author pn
 * @since 2024-02-24
 */
public interface ParamsDetailsMapper extends BaseMapper<ParamsDetails> {

    ArrayList<ParamsDetails> getAll(long id);
}
