package com.briup.product_source.exception;


import com.briup.product_source.util.ResultCode;

/**
 * 用户自定义异常（业务异常）
 */
public class CustomException extends RuntimeException{
    private ResultCode code;
    public CustomException(ResultCode rc) {
        super(rc.getMessage());
        this.code = rc;
    }

    public ResultCode getCode() {
        return code;
    }
}
