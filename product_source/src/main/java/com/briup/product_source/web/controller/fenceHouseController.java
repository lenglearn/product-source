package com.briup.product_source.web.controller;

import com.briup.product_source.bean.basic.FenceHouse;
import com.briup.product_source.bean.ext.FenceHouseExt;
import com.briup.product_source.service.FenceHouseService;
import com.briup.product_source.util.Result;
import com.briup.product_source.util.ResultUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "栏舍管理")
@RequestMapping("/fenceHouse")
@Slf4j
public class fenceHouseController {
    @Resource
    private FenceHouseService fenceHouseService;


    @ApiOperation("分页多条件查询栏舍信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = true),
            //name值是前端传递参名 @RequestParam中value值
            @ApiImplicitParam(name = "fhName",value = "栏舍名字关键字")
    })
    @GetMapping("/query")
    public Result<PageInfo<FenceHouse>> findByPage(@RequestParam(value = "pageNum",required = true) Integer pageNum,
                                                   @RequestParam(required = true) Integer pageSize,
                                                   @RequestParam(value = "fhName",required = false) String name){
        PageInfo<FenceHouse> page = fenceHouseService.findByPage(pageNum, pageSize, name);
        return ResultUtil.success(page);
    }

    @GetMapping("/selectById")
    @ApiOperation("根据栏舍编号查询栏舍信息以及栏圈信息接口")
    @ApiImplicitParam(name = "fhId",value = "栏舍编号",required = true)
    public Result<FenceHouseExt> findById(@RequestParam(required = true) String fhId) {
        FenceHouseExt result = fenceHouseService.findById("851d58388f974d95b6c67d93189b7222");
        return ResultUtil.success(result);
    }


    @PostMapping("/saveOrUpdate")
    @ApiOperation("新增或修改栏舍接口")
    public Result saveOrUpdate(@RequestBody FenceHouse fenceHouse) {
        fenceHouseService.saveOrUpdate(fenceHouse);
        return ResultUtil.success();
    }



    @ApiOperation("批量删除栏舍接口")
    @DeleteMapping("/deleteByIdAll")
    public Result deleteByids(@RequestBody List<String> ids) throws Exception{
        fenceHouseService.deleteByBatch(ids);
        return ResultUtil.success();
    }


    @ApiOperation("根据id删除栏舍信息接口")
    @ApiImplicitParam(name = "hId",value = "栏舍编号",required = true)
    @DeleteMapping("/deleteById/{hId}")
    public Result deletById(@PathVariable("hId") String id){
        fenceHouseService.deleteById(id);
        return ResultUtil.success();
    }
}
