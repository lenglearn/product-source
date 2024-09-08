package com.briup.product_source.dao.basic;

import com.briup.product_source.bean.basic.FenceHouse;

public interface FenceHouseMapper {
    int deleteByPrimaryKey(String fhId);

    int insert(FenceHouse record);

    int insertSelective(FenceHouse record);

    FenceHouse selectByPrimaryKey(String fhId);

    int updateByPrimaryKeySelective(FenceHouse record);

    int updateByPrimaryKey(FenceHouse record);
}