package com.pn.service.impl;

import com.pn.domain.po.UserInterfaceInfo;
import com.pn.mapper.UserInterfaceInfoMapper;
import com.pn.service.UserInterfaceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户调用接口关系 服务实现类
 * </p>
 *
 * @author pn
 * @since 2024-02-08
 */
@Service
@RequiredArgsConstructor
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo> implements UserInterfaceInfoService {
    private final UserInterfaceInfoMapper userInterfaceInfoMapper;
    @Override
    public void getCount(String interfaceId, String userId) {

         userInterfaceInfoMapper.getCount(interfaceId,userId);
    }

    @Override
    public UserInterfaceInfo findInterfaceIdByUserId(String userId, String interfaceId) {
        return userInterfaceInfoMapper.findInterfaceIdByUserId(userId,interfaceId);
    }

    @Override
    public UserInterfaceInfo findLeftNumByUserId(String userId, String interfaceId) {
        return userInterfaceInfoMapper.findLeftNumByUserId(userId,interfaceId);
    }



}
