package com.pn.service.impl;

import com.pn.domain.po.InterfaceInfo;
import com.pn.domain.vo.InterfaceInfoVo;
import com.pn.mapper.InterfaceInfoMapper;
import com.pn.service.InterfaceInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 * 接口信息 服务实现类
 * </p>
 *
 * @author pn
 * @since 2024-02-02
 */
@Service
public class InterfaceInfoServiceImpl implements InterfaceInfoService {
   @Resource
    private  InterfaceInfoMapper interfaceInfoMapper;
    @Override
    public ArrayList<InterfaceInfoVo> getInterfaceInfo(int currentPage, int pageSize) {
        return interfaceInfoMapper.getInterfaceInfoPage(currentPage,pageSize);
    }

    @Override
    public InterfaceInfo getInterfaceInfo(int id) {
        return interfaceInfoMapper.getInterfaceInfo(id);
    }
}
