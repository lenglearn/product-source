package com.briup.product_source.util;

/**
 * 响应信息的枚举类：
 */
public enum ResultCode {
    SUCCESS(200,"操作成功"),
    ERROR(0,"操作失败"),

    //操作成功：2000-2999
    ADD_SUCCESS(2000,"新增成功"),
    UPDATE_SUCCESS(2000,"修改成功"),
    DELETE_SUCCESS(2000,"删除成功"),


    //参数错误： 4000-4999
    USER_ID_ERROR(4000,"用户ID不合法"),
    USER_NAME_REPEAT(4001,"用户名重复"),
    USER_PASSWORD_NULL(4002,"密码不能为空"),
    USER_PASSWORD_LENGTH(4003,"密码长度不能小于6位"),
    PAGE_PARGNUM_EORROR(4004,"分页参数不合法"),
    NAME_NOT_EMPTY(4005,"名字不能为空"),
    DESC_NOT_EMPTY(4006,"描述信息不能为空" ),
    DATA_NOT_EXIST(4007,"数据不存在" ),
    ID_NOT_EXIST(4008,"ID不存在"),
    FH_NAME_REPEAT(4009,"栏舍名称重复"),
    TOKEN_INVALID(4010,"token无效，请重新获取"),
    USER_NOT_LOGIN(4011,"用户未登录，请登录"),
    USER_STATUS_ERROR(4012,"账号被禁用"),
    USER_NAME_ERROR(4013,"用户名不存在"),
    USER_PASSWD_ERROR(4014,"用户密码错误"),
    H_SAVED_FULL(4015,"栏圈已满"),



    //服务器内部错误  5000-5999
    SERVER_INTER_ERROR(5000,"程序内部错误,请联系管理员");



    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    //为了获取具体的响应信息，不提供set方法，防止被其他地方修改

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}