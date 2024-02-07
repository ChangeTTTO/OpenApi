package com.pn.controller;


import com.pn.api.controller.Test;
import com.pn.common.Result;
import com.pn.domain.dto.invokeDTO;
import com.pn.openfeign.client.apiClient.ApiClient;
import com.pn.service.InterfaceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
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
@Tag(name = "接口信息")
public class InterfaceInfoController {
    @Resource
    private  InterfaceInfoService interfaceInfoService;

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
    public Result invokeInterface(@RequestBody invokeDTO invokeDTO) {
        try {
            // 1.获取 Test 类型
            Test test = new Test();
            Class<?> clientClass = test.getClass();
            // 2.获取名为 参数name 的方法
            Method method = clientClass.getMethod(invokeDTO.getMethodName(), Object.class);
            return Result.success(method.invoke(test,invokeDTO.getParams()));


        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return null; // 或者返回适当的错误信息
        }
    }

    }

