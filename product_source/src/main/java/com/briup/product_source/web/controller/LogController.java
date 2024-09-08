package com.briup.product_source.web.controller;

import com.briup.product_source.util.Result;
import com.briup.product_source.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页查询日志功能
 */
@RestController
@Api(tags = "日志模块")
@RequestMapping("/log")
public class LogController {
    @GetMapping("/page")
    @ApiOperation("分页查询日志信息")
    public Result findByPage(HttpServletRequest request){
        String host = request.getRemoteHost();
        return ResultUtil.success(host);
    }
}
