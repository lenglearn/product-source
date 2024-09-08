package com.briup.product_source.dao.basic;

import com.briup.product_source.bean.basic.Freezer;

public interface FreezerMapper {
    int deleteByPrimaryKey(Integer fzId);

    int insert(Freezer record);

    int insertSelective(Freezer record);

    Freezer selectByPrimaryKey(Integer fzId);

    int updateByPrimaryKeySelective(Freezer record);

    int updateByPrimaryKey(Freezer record);
}