package com.briup.product_source.util;

/**
 * 统一返回结果类
 */
public class Result<T> {
    private Integer code;
    //需要注意：
    private String message;
    private T data;

    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    //使用枚举对象进行初始化
    public Result(ResultCode rc, T data) {
        this.code = rc.getCode();
        this.message = rc.getMessage();
        this.data = data;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
