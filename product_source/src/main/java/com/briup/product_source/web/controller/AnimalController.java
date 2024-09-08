package com.briup.product_source.web.controller;

import com.briup.product_source.bean.basic.Animal;
import com.briup.product_source.bean.ext.AnimalExt;
import com.briup.product_source.bean.ext.ManagerAnimalExt;
import com.briup.product_source.service.AnimalService;
import com.briup.product_source.util.Result;
import com.briup.product_source.util.ResultUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "动物管理")
@RequestMapping("/animal")
public class AnimalController {
    @Autowired
    private AnimalService animalService;
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Animal animal){
        animalService.saveOrUpdate(animal);
        return ResultUtil.success();
    }


    @ApiOperation("批量删除动物接口")
    @DeleteMapping("/deleteByIdAll")
    public Result deleteByids(@RequestBody List<String> ids) throws Exception{
        animalService.deleteByBatch(ids);
        return ResultUtil.success();
    }


    @ApiOperation("根据id删除动物信息接口")
    @ApiImplicitParam(name = "aAnimalId",value = "动物编号",required = true)
    @DeleteMapping("/deleteById/{aAnimalId}")
    public Result deletById(@PathVariable("aAnimalId") String id){
        animalService.deleteById(id);
        return ResultUtil.success();
    }

    @ApiOperation("分页并根据条件查询动物基本信息以及对应批次和栏圈信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页",required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = true),
            @ApiImplicitParam(name = "aHealthy",value = "健康状态",allowableValues = "健康,生病"),
            @ApiImplicitParam(name = "aGender",value = "性别",allowableValues = "雌性,雄性")
    })
    @GetMapping("/query")
    public Result<PageInfo<Animal>> getAnimals(@RequestParam(value = "pageNum",required = true) Integer pageNum,
                                                  @RequestParam(value = "pageSize",required = true) Integer pageSize,
                                                  @RequestParam(value = "aHealthy",required = false) String aHealthy,
                                                  @RequestParam(value = "aGender",required = false) String aGender){
        PageInfo<AnimalExt> page = animalService.findByPage(pageNum, pageSize, aHealthy, aGender);
        return ResultUtil.success(page);
    }
    @ApiOperation("根据动物编号查询动物基本信息以及对应批次和栏舍栏圈信息")
    @GetMapping("/findByAnimalId")
    public Result<ManagerAnimalExt> findById(@RequestParam(value = "animalId",required = true) String id){
        ManagerAnimalExt result = animalService.findById(id);
        return ResultUtil.success(result);
    }
}
