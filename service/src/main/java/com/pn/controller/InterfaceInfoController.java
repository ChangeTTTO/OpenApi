package com.pn.controller;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ReflectUtil;
import com.pn.common.Result;
import com.pn.domain.dto.invokeDTO;
import com.pn.domain.po.UserInterfaceInfo;
import com.pn.feign.client.ApiClient;
import com.pn.mapper.CommonMapper;
import com.pn.mapper.InterfaceInfoMapper;
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

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;
    @GetMapping("/all")
    @Operation(summary = "分页获取免费接口信息")

    public Result getInterfaceInfo(Integer currentPage, Integer pageSize){
        return Result.success(interfaceInfoService.getInterfaceInfo(currentPage,pageSize));
    }
    @GetMapping("/getVipInterface")
    @Operation(summary = "获取vip接口信息")

    public Result getVipInterface(){
        return Result.success(interfaceInfoMapper.getVipInterface());
    }
    @GetMapping("/{id}")
    @Operation(summary = "查看接口信息")
    public Result getInterfaceInfo(@PathVariable int id) {
        return Result.success(interfaceInfoService.getInterfaceInfo(id));
    }

    /**
     * 根据方法名调用接口
     */
    @PostMapping("/invoke")
    @Operation(summary = "目标接口调用")
    public Object invokeInterface(@RequestBody invokeDTO invokeDTO) {
        Object invoke;
        String interfaceName = invokeDTO.getInterfaceName();
        Object params = invokeDTO.getRequestParams();
        //1.如果该接口剩余调用次数为0，将不允许调用
        UserInterfaceInfo user = userInterfaceInfoService.findLeftNumByUserId(invokeDTO.getUserId(), invokeDTO.getInterfaceId());
        Integer leftNum = user.getLeftNum();
        Long id = user.getId();
        if (leftNum==0){
            return "次数已用尽";
        }

        //2.接口id不存在，将返回错误
        if (!Objects.equals(invokeDTO.getInterfaceId(), user.getInterfaceInfoId())){
            return "接口id错误";
        }
        //3.根据有无参数调用目标接口
        if (ObjUtil.isNull(params)){
             invoke = ReflectUtil.invoke(apiClient, interfaceName);
        }else {
            invoke = ReflectUtil.invoke(apiClient, interfaceName,params);
        }
        //3.调用后该接口剩余调用次数-1
        commonMapper.decrementCount("user_interface_info", String.valueOf(id),"leftNum");

            return invoke;
        }

        @GetMapping("/test")
        public Result test(){
        return Result.success();
        }
    /**
     * 获取接口调用次数
     */
    @PostMapping("/getCount")
    @Operation(summary = "获取接口调用次数")

    public Result getCount(@RequestBody invokeDTO invokeDTO){
        //根据用户id和接口id查找数据,查看是否有对应关系
        UserInterfaceInfo id = userInterfaceInfoService.findInterfaceIdByUserId(invokeDTO.getUserId(),invokeDTO.getInterfaceId());
        if (id!=null){
            return Result.error("不可重复获取调用次数");
        }
        userInterfaceInfoService.getCount(invokeDTO.getInterfaceId(),invokeDTO.getUserId());
        return Result.success("获取成功");
    }

    /**
     * 根据用户id和接口id接口对应关系
     */
    @PostMapping("/findInterfaceIdByUserId")
    @Operation(summary = "根据用户id和接口id接口对应关系")
    public Result findInterfaceIdByUserId(invokeDTO invokeDTO){
        return Result.success(userInterfaceInfoService.findInterfaceIdByUserId(invokeDTO.getUserId(),invokeDTO.getInterfaceId())) ;
    }
    }


