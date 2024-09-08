package com.briup.product_source.dao.ext;

import com.briup.product_source.bean.basic.Account;
import com.briup.product_source.dao.basic.AccountMapper;

public interface AccountExtMapper extends AccountMapper {
    /**
     * 根据用户名查询对应用户信息
     * @param username
     * @return
     */
    Account selectByName(String username);
}
