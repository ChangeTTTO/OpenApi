package com.pn.mapper;

import com.pn.domain.po.UserInterfaceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户调用接口关系 Mapper 接口
 * </p>
 *
 * @author pn
 * @since 2024-02-08
 */
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    void getCount(String interfaceId, String userId);

    UserInterfaceInfo findInterfaceIdByUserId(String userId,String interfaceId);

    UserInterfaceInfo findLeftNumByUserId(String userId,String interfaceId);
}
