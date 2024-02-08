package com.pn.controller;


import com.pn.common.Result;
import com.pn.domain.dto.invokeDTO;
import com.pn.domain.po.UserInterfaceInfo;
import com.pn.service.UserInterfaceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户调用接口关系 前端控制器
 * </p>
 *
 * @author pn
 * @since 2024-02-08
 */
@Tag(name = "用户接口调用关系接口 ")
@RestController
@RequestMapping("/userInterfaceInfo")
@RequiredArgsConstructor
public class UserInterfaceInfoController {
    private final UserInterfaceInfoService userInterfaceInfoService;
    /**
    * 获取接口调用次数
    */
    @PostMapping("/getCount")
    @Operation(summary = "获取接口调用次数")
    public Result getCount(@RequestBody invokeDTO invokeDTO){
        UserInterfaceInfo id = userInterfaceInfoService.findInterfaceIdByUserId(invokeDTO.getUserId());
        if (id!=null){
            return Result.error("不可重复获取调用次数");
        }
        userInterfaceInfoService.getCount(invokeDTO.getInterfaceId(),invokeDTO.getUserId());
            return Result.success("获取成功");
    }

    /**
     * 根据用户id找查接口id
     */
    @PostMapping("/findInterfaceIdByUserId")
    @Operation(summary = "根据用户id找查接口id")
    public Result findInterfaceIdByUserId(invokeDTO invokeDTO){
        return Result.success(userInterfaceInfoService.findInterfaceIdByUserId(invokeDTO.getUserId())) ;
    }
}
