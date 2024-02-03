package com.pn.service;

import com.pn.domain.po.InterfaceInfo;
import com.pn.domain.vo.InterfaceInfoVo;

import java.util.ArrayList;

/**
 * <p>
 * 接口信息 服务类
 * </p>
 *
 * @author pn
 * @since 2024-02-02
 */

public interface InterfaceInfoService{

    ArrayList<InterfaceInfoVo> getInterfaceInfo(int currentPage, int pageSize);
    InterfaceInfo getInterfaceInfo(int id);
}
