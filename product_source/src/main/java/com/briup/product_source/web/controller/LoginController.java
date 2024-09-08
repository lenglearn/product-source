package com.briup.product_source.web.controller;

import com.briup.product_source.aop.Logging;
import com.briup.product_source.bean.basic.Account;
import com.briup.product_source.service.AccountService;
import com.briup.product_source.util.Result;
import com.briup.product_source.util.ResultUtil;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
@Api(tags = "登录模块")
public class LoginController {
    @Autowired
    private AccountService service;

    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "form"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,paramType = "form")
    })
    @Logging("登录操作") //表示记录日志操作
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Result<Map<String, String>> login(@RequestParam String username, @RequestParam String password){
        log.info("用户名:{} 密码：{}",username,password);

        String token = service.login(username, password);

        return ResultUtil.success(ImmutableMap.of("token",token));
    }
    @ApiOperation("退出登录接口")
    @Logging("退出登录操作")
    @GetMapping("/logout")
    public Result logout(){
        return ResultUtil.success();
    }

    @ApiOperation("获取当前登录用户信息接口")
    @Logging("获取当前登录用户信息操作")
    @GetMapping("/me")
    public Result findInfo(HttpServletRequest request){
        //获取请求头的信息
        String token = request.getHeader("token");
        Account account = service.findLoginUser(token);
        return ResultUtil.success(account);
    }
}
