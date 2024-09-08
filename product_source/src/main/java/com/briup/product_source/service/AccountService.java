package com.briup.product_source.service;

import com.briup.product_source.bean.basic.Account;

public interface AccountService {
    /**
     * 登录接口
     * @param username 用户名
     * @param password 密码
     * @return token字符串
     */
    String login(String username,String password);
    /**
     * 根据token字符串获取账号信息
     * @param token
     * @return
     */
     Account findLoginUser(String token);
}
