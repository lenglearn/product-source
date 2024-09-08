package com.briup.product_source.dao.basic;

import com.briup.product_source.bean.basic.Hurdles;

public interface HurdlesMapper {
    int deleteByPrimaryKey(String hId);

    int insert(Hurdles record);

    int insertSelective(Hurdles record);

    Hurdles selectByPrimaryKey(String hId);

    int updateByPrimaryKeySelective(Hurdles record);

    int updateByPrimaryKey(Hurdles record);
}