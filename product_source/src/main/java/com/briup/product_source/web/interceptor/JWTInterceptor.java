package com.briup.product_source.web.interceptor;
import com.briup.product_source.exception.CustomException;
import com.briup.product_source.util.BriupAssert;
import com.briup.product_source.util.JwtUtil;
import com.briup.product_source.util.ResultCode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器，当用户访问资源时，进行拦截
 * 当拦截到基于请求url拦截时，自动调用拦截器中方法
 * 配置拦截器的规则：
 */
/**
 * jwt登录验证，验证用户是否有权限访问资源
 */
@Component //创建拦截器对象
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //当用户登录请求时，直接拦截通过
        if("/login".equals(request.getRequestURI())){
            return true;
        }
        System.out.println(request.getRequestURI());
        //核心功能：获取请求头中token字符串，验证是否合法 是否存在
        String token = request.getHeader("token");
        //当token为空，用户未登录直接访问资源，拦截不通过，提示用户请登录
        BriupAssert.notNull(token,ResultCode.USER_NOT_LOGIN);
        //当token不为空，验证是否合法的身份信息
        try{
            JwtUtil.checkSign(token);
        }catch (Exception ex){
            throw new CustomException(ResultCode.TOKEN_INVALID);
        }
        return true;//默认通过拦截
    }
}
