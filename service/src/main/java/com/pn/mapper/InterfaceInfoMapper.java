package com.pn.mapper;

import com.pn.domain.po.InterfaceInfo;
import com.pn.domain.vo.InterfaceInfoVo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * <p>
 * 接口信息 Mapper 接口
 * </p>
 *
 * @author pn
 * @since 2024-02-02
 */
@Repository
public interface InterfaceInfoMapper {
    ArrayList<InterfaceInfoVo> getInterfaceInfoPage(int currentPage, int pageSize);
    InterfaceInfo getInterfaceInfo(int id);
    ArrayList<InterfaceInfoVo> getVipInterface();
}

