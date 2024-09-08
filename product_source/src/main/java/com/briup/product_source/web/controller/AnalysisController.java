package com.briup.product_source.web.controller;


import com.briup.product_source.service.AnalysisService;
import com.briup.product_source.util.Result;
import com.briup.product_source.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analysis")
@Api(tags = "首页大屏")
public class AnalysisController {
    @Resource
    private AnalysisService service;

    @GetMapping("/count")
    @ApiOperation("统计栏舍，栏圈，动物数量，冷库数量，员工数量信息接口")
    public Result<Map<String, List<Object>>> countNum() {
        Map<String, List<Object>> map = service.countNum();
        return ResultUtil.success(map);
    }


    @ApiOperation("统计动物体重信息接口")
    @GetMapping("/indexCount")
    public Result<Map<String, Integer>> countWeight() {
        Map<String, Integer> map = service.countWeight();
        return ResultUtil.success(map);
    }



    @ApiOperation("统计动物病症数量接口")
    @GetMapping("/countDisease")
    public Result<Map<String, Integer>> countDiseaseByName() {
        Map<String, Integer> map = service.countDiseaseByName();
        return ResultUtil.success(map);
    }


    @ApiOperation("统计本年度12个月销售动物接口")
    @GetMapping("/countSales")
    public Result<Map<String, List<Object>>> countSales(){
        Map<String, List<Object>> map = service.countSales();
        return ResultUtil.success(map);
    }
}
