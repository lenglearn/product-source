package com.briup.product_source.service.impl;

import com.briup.product_source.bean.basic.Account;
import com.briup.product_source.dao.ext.AccountExtMapper;
import com.briup.product_source.service.AccountService;
import com.briup.product_source.util.BriupAssert;
import com.briup.product_source.util.JwtUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

import static com.briup.product_source.constant.BriupConstant.ACCOUNT_ENABLE;
import static com.briup.product_source.util.ResultCode.*;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountExtMapper mapper;

    @Override
    public String login(String username, String password) {
        //1.判断用户名是否存在
        Account account = mapper.selectByName(username);
        BriupAssert.notNull(account, USER_NAME_ERROR);
        //2.判断密码是否正确
        BriupAssert.isTrue(Objects.equals(password,account.getPassword()),
                           USER_PASSWD_ERROR);
        //3.判断账号是否禁用
        BriupAssert.isTrue(account.getStatus() == ACCOUNT_ENABLE,USER_STATUS_ERROR);
        //4.验证通过，返回用户认证信息
        Map userInfo = ImmutableMap.of("email", account.getEmail(),
                                       "realName", account.getRealname());
        return JwtUtil.sign(account.getAccountId(), userInfo);
    }

    @Override
    public Account findLoginUser(String token) {
        //解析jwt字符串获取用户编号 md5
        String userId = JwtUtil.getUserId(token);
        return mapper.selectByPrimaryKey(userId);
    }
}
