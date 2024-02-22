package com.pn.controller;


import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ReflectUtil;
import com.pn.common.Result;
import com.pn.domain.dto.invokeDTO;
import com.pn.domain.po.UserInterfaceInfo;
import com.pn.feign.client.ApiClient;
import com.pn.mapper.CommonMapper;

import com.pn.service.InterfaceInfoService;
import com.pn.service.UserInterfaceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 * 接口信息 前端控制器
 * </p>
 * @author pn
 * @since 2024-02-02
 */
@RestController
@RequestMapping("/interfaceInfo")
@Tag(name = "接口信息")

public class InterfaceInfoController {
    @Resource
    private  InterfaceInfoService interfaceInfoService;
    @Resource
    private  UserInterfaceInfoService userInterfaceInfoService;
    @Resource
    private ApiClient apiClient;
    @Resource
    private  CommonMapper commonMapper;

    @GetMapping("/all")
    @Operation(summary = "分页获取所有接口信息")
    @CrossOrigin
    public Result getInterfaceInfo(Integer currentPage,Integer pageSize){
        return Result.success(interfaceInfoService.getInterfaceInfo(currentPage,pageSize));
    }
    @GetMapping("/{id}")
    @CrossOrigin
    @Operation(summary = "查看接口信息")
    public Result getInterfaceInfo(@PathVariable int id) {
        return Result.success(interfaceInfoService.getInterfaceInfo(id));
    }

    /**
     * 根据方法名调用接口
     */
    @PostMapping("/invoke")
    @Operation(summary = "目标接口调用")
    public Result invokeInterface(@RequestBody invokeDTO invokeDTO) {
        Object invoke;
        String interfaceName = invokeDTO.getInterfaceName();
        Object params = invokeDTO.getRequestParams();
        //1.如果该接口剩余调用次数为0，将不允许调用
        UserInterfaceInfo user = userInterfaceInfoService.findLeftNumByUserId(invokeDTO.getUserId(), invokeDTO.getInterfaceId());
        Integer leftNum = user.getLeftNum();
        Long id = user.getId();
        if (leftNum==0){
            return Result.error("调用次数已耗尽");
        }
        //2.接口id不存在，将返回错误
        if (!Objects.equals(invokeDTO.getInterfaceId(), user.getInterfaceInfoId())){
            return Result.error("接口id错误");
        }
        //3.根据有无参数调用目标接口
        if (ObjUtil.isEmpty(params)){
             invoke = ReflectUtil.invoke(apiClient, interfaceName);
        }else {
            invoke = ReflectUtil.invoke(apiClient, interfaceName,params);
        }
        //3.调用后该接口剩余调用次数-1
        commonMapper.decrementCount("user_interface_info", String.valueOf(id),"leftNum");

            return Result.success(invoke);
        }
    }


