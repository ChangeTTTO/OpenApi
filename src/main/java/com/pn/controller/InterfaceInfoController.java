package com.pn.controller;


import com.pn.common.Result;
import com.pn.service.InterfaceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 接口信息 前端控制器
 * </p>
 * @author pn
 * @since 2024-02-02
 */
@RestController
@RequestMapping("/interface")
@Tag(name = "接口信息")
public class InterfaceInfoController {
    @Resource
    private  InterfaceInfoService interfaceInfoService;
    @GetMapping("/all")
    @Operation(summary = "分页获取所有接口信息")
    public Result getInterfaceInfo(Integer currentPage,Integer pageSize){
        return Result.success(interfaceInfoService.getInterfaceInfo(currentPage,pageSize));
    }
    @GetMapping
    @Operation(summary = "查看接口信息")
    public Result getInterfaceInfo(int id){
        return Result.success(interfaceInfoService.getInterfaceInfo(id));
    }
}
