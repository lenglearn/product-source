package com.briup.product_source.dao.basic;

import com.briup.product_source.bean.basic.Animal;

public interface AnimalMapper {
    int deleteByPrimaryKey(String aAnimalId);

    int insert(Animal record);

    int insertSelective(Animal record);

    Animal selectByPrimaryKey(String aAnimalId);

    int updateByPrimaryKeySelective(Animal record);

    int updateByPrimaryKey(Animal record);
}