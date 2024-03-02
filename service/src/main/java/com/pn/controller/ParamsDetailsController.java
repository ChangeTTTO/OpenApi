package com.pn.controller;


import com.pn.common.Result;
import com.pn.domain.po.ParamsDetails;
import com.pn.mapper.ParamsDetailsMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * <p>
 * 参数细节 前端控制器
 * </p>
 *
 * @author pn
 * @since 2024-02-24
 */
@RestController
@RequestMapping("/ParamsDetails")
@Tag(name = "参数详情接口")
public class ParamsDetailsController {
    @Resource
    private ParamsDetailsMapper paramsDetailsMapper;
/**
 * 根据接口id查询所有
 */
@GetMapping("/getAll")
public Result getAll(long id){
    ArrayList<ParamsDetails> Details = paramsDetailsMapper.getAll(id);
    return Result.success(Details);
}
}
