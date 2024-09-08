package com.briup.product_source.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解类：表示是否在当前方法添加日志功能
 */
@Retention(RetentionPolicy.RUNTIME) //在运行期间
@Target(ElementType.METHOD) //当前注解在方法上使用
public @interface Logging {
    //表示用户日志操作的含义
    String value() default "";
}