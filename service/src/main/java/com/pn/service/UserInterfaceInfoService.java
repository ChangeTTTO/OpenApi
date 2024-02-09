package com.pn.service;

import com.pn.domain.po.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户调用接口关系 服务类
 * </p>
 *
 * @author pn
 * @since 2024-02-08
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

     void getCount(String interfaceId, String userId);

     UserInterfaceInfo findInterfaceIdByUserId(String userId);
     UserInterfaceInfo findLeftNumByUserId(String userId);
}
