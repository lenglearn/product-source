package com.briup.product_source.dao.basic;

import com.briup.product_source.bean.basic.Batch;

public interface BatchMapper {
    int deleteByPrimaryKey(String bSerialId);

    int insert(Batch record);

    int insertSelective(Batch record);

    Batch selectByPrimaryKey(String bSerialId);

    int updateByPrimaryKeySelective(Batch record);

    int updateByPrimaryKey(Batch record);
}