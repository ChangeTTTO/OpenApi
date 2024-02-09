package com.pn.controller;


import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.pn.api.controller.DogController;
import com.pn.api.controller.Test;
import com.pn.common.Result;
import com.pn.domain.dto.invokeDTO;
import com.pn.domain.po.UserInterfaceInfo;
import com.pn.mapper.CommonMapper;
import com.pn.openfeign.client.apiClient.ApiClient;
import com.pn.service.InterfaceInfoService;
import com.pn.service.UserInterfaceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

/**
 * <p>
 * 接口信息 前端控制器
 * </p>
 * @author pn
 * @since 2024-02-02
 */
@RestController
@RequestMapping("/interfaceInfo")
@RequiredArgsConstructor
@Tag(name = "接口信息")
public class InterfaceInfoController {
    @Resource
    private  InterfaceInfoService interfaceInfoService;
    private final UserInterfaceInfoService userInterfaceInfoService;
    private final ApiClient apiClient;
    private final CommonMapper commonMapper;

    @GetMapping("/all")
    @Operation(summary = "分页获取所有接口信息")
    public Result getInterfaceInfo(Integer currentPage,Integer pageSize){
        return Result.success(interfaceInfoService.getInterfaceInfo(currentPage,pageSize));
    }
    @GetMapping("/{id}")
    @Operation(summary = "查看接口信息")
    public Result getInterfaceInfo(@PathVariable int id) {
        return Result.success(interfaceInfoService.getInterfaceInfo(id));
    }
    @PostMapping("/invoke")
    @Operation(summary = "目标接口调用")
    public Result invokeInterface(@RequestBody invokeDTO invokeDTO) {
        Object invoke;
        String interfaceName = invokeDTO.getInterfaceName();
        Object params = invokeDTO.getRequestParams();
        //1.如果该接口剩余调用次数为0，将不允许调用
        UserInterfaceInfo user = userInterfaceInfoService.findLeftNumByUserId(invokeDTO.getUserId());
        Integer leftNum = user.getLeftNum();
        if (leftNum==0){
            return Result.error("调用次数已耗尽");
        }
        //2.根据有无参数调用目标接口
        if (ObjUtil.isEmpty(params)){
             invoke = ReflectUtil.invoke(apiClient, interfaceName);
        }else {
            invoke = ReflectUtil.invoke(apiClient, interfaceName,params);
        }
        //3.调用后该接口剩余调用次数-1
        commonMapper.decrementCount("user_interface_info",invokeDTO.getInterfaceId(),"leftNum");

            return Result.success(invoke);
        }
    }


