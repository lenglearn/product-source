package com.briup.product_source.dao.basic;

import com.briup.product_source.bean.basic.Disease;

public interface DiseaseMapper {
    int deleteByPrimaryKey(Integer dId);

    int insert(Disease record);

    int insertSelective(Disease record);

    Disease selectByPrimaryKey(Integer dId);

    int updateByPrimaryKeySelective(Disease record);

    int updateByPrimaryKey(Disease record);
}