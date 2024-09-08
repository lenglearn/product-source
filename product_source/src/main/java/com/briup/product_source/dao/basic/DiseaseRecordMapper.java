package com.briup.product_source.dao.basic;

import com.briup.product_source.bean.basic.DiseaseRecord;

public interface DiseaseRecordMapper {
    int deleteByPrimaryKey(Integer drId);

    int insert(DiseaseRecord record);

    int insertSelective(DiseaseRecord record);

    DiseaseRecord selectByPrimaryKey(Integer drId);

    int updateByPrimaryKeySelective(DiseaseRecord record);

    int updateByPrimaryKey(DiseaseRecord record);
}