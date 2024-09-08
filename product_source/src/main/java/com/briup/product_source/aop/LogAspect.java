package com.briup.product_source.aop;

import com.briup.product_source.bean.basic.Account;
import com.briup.product_source.bean.basic.Log;
import com.briup.product_source.dao.basic.LogMapper;
import com.briup.product_source.dao.ext.AccountExtMapper;
import com.briup.product_source.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;


/**
 * 日志切面类功能：
 * 1.当前切面类获取日志信息：(通过ioc容器获取请求对象)
 *      （1）请求url method host
 *      (2) 登录后的用户信息
 *      (3) 请求的时间
 * 2.将获取日志信息保存在数据库中base_log
 *   XXXMapper对象 执行插入语句
 *
 */
@Slf4j //创建一个日志对象
@Aspect //切面类=切入点规则+通知
@Component //当前是组件
public class LogAspect {
    @Autowired
    private HttpServletRequest request;//IOC容器获取请求对象
    @Autowired
    private AccountExtMapper accountMapper;
    @Autowired
    private LogMapper logMapper;


    @Pointcut("execution(* com.briup.product_source.web.controller.*.*(..)) && @annotation(Logging)")
    public void logPointCut(){
        //选择所有的web层的请求方法作为切入点
    }

    //前置通知或环绕通知，完成日志记录
    @Before("logPointCut()")
    public void beforeAdvice(){//切入点方法执行前，先执行前置通知代码
        //1.获取用户的日志信息
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();
        //2.通过解析请求头token获取用户的username信息
        String token = request.getHeader("token");
        String username = null;
        String realname = null;
        Account account = null;
        if(token == null){//登录操作
            username = request.getParameter("username");
            account = accountMapper.selectByName(username);
        }else {
            //获取账号编号,解析获取用户的其他信息
            account = accountMapper.selectByPrimaryKey(JwtUtil.getUserId(token));
            username = account.getUsername();
        }
        if(account != null){
            realname = account.getRealname();
        }
        //将这个信息通过建造者模式封装到Log对象，保存数据库中
        //Log-->LogBuidler对象-->build-->Log对象
        Log log = Log.builder().logRequestUri(uri)
                .logRequestMethod(method)
                .logTime(new Date())
                .logRealname(realname)
                .logUsername(username)
                .logHost(ip)
                .build();
        logMapper.insertSelective(log);

    }
    //升级日志功能：通过使用日志框架实现打印日志到控制台
    // /login                用户登录
    // 日期 - xxxx -xxx -xxx ：用户登录
    // 日期                  ：退出登录
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //pjp对象就是表示当前的切入点的信息 （方法的信息）
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        //通过method对象获取到指定的方法上的注解信息
        Logging logging = method.getAnnotation(Logging.class);
        String methodDesc = logging.value();
        log.info("当前用户操作：{}",methodDesc);
        //获取用户的请求参数
        Object[] args = pjp.getArgs();
        log.info("当前用户参数：{}", Arrays.toString(args));
        Object result = pjp.proceed();
        return result;
    }

    /*
        异常通知：
        1.web层如何处理？通过全局异常处理器，返回统一响应格式数据
          @RestControllerAdvice  调用指定的
        2.service如何处理？直接抛出web
            事务操作：@Transactional  当service 2个 delete
            放在同一个session，当发送异常，进行回滚操作 rollback();

        3.DAO层如何处理异常？直接抛出service->web

     */
    //使用pagehelper对查询结果进行分页，灵活性高，
}
