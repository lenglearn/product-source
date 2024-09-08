package com.briup.product_source.dao.ext;

import com.briup.product_source.bean.basic.Animal;
import com.briup.product_source.bean.ext.AnimalExt;
import com.briup.product_source.bean.ext.ManagerAnimalExt;
import com.briup.product_source.dao.basic.AnimalMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnimalExtMapper extends AnimalMapper {
    void deleteByIds(List<String> ids);


    List<AnimalExt> findAnimals(@Param("aHealthy") String aHealthy, @Param("aGender") String aGender);


    ManagerAnimalExt findAnimalWithBatch(String id);
}
