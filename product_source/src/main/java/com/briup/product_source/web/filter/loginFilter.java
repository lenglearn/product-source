package com.briup.product_source.web.filter;

import com.briup.product_source.exception.CustomException;
import com.briup.product_source.util.BriupAssert;
import com.briup.product_source.util.JwtUtil;
import com.briup.product_source.util.ResultCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//@WebFilter("/*")
@Slf4j
public class loginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //不拦截跨域请求OPTIONS
        if(Objects.equals(req.getMethod(),"OPTIONS")){
            chain.doFilter(request, response);
        }


        String uri = req.getRequestURI();
        //创建一个集合对象表示放行的路径
        List<String> pathList = Arrays.asList("/swagger-resources",
                "/webjars",
                "/v2/api-docs",
                "/swagger-ui.html",
                "/csrf",
                "/error",
                "/doc.html",
                "/favicon.ico");
        //当请求路径符合当前路径中任意一个，
        /*
            如果请求路径中前缀是一下任意一个路径开头，表示通过
            /student/login/all  /login
         */
        boolean flag = pathList.stream()//
                .anyMatch(path -> uri.startsWith(path));//是否包含放行的路径关键字
        if(flag){ //不进行拦截,继续访问资源
            chain.doFilter(request, response);
            return;
        }
        try{
            String token = req.getHeader("token");
            BriupAssert.notNull(token,ResultCode.USER_NOT_LOGIN);//抛出异常，需要转发给其他的controller
            try{
                JwtUtil.checkSign(token);
                //继续访问其他资源，放行
                chain.doFilter(request, response);
            }catch (Exception ex){
                throw new CustomException(ResultCode.TOKEN_INVALID);
            }
        }catch (Exception ex){
            log.info("拦截的路径：{}",uri);
            //将异常信息保存请求对象
            req.setAttribute("exception",ex);
            //将异常的信息转发ErrorController处理
            req.getRequestDispatcher("/filterError").forward(request, response);
        }
    }
}
