package com.briup.product_source.dao.ext;

import com.briup.product_source.bean.basic.FenceHouse;
import com.briup.product_source.bean.ext.FenceHouseExt;
import com.briup.product_source.dao.basic.FenceHouseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通过逆向工程生成的映射接口 映射文件 业务类中
 * 不能进行代码的编写，重新逆向工程会覆盖原来的文件
 */
public interface FenceHouseExtMapper extends FenceHouseMapper {

    List<FenceHouse> selectByName(String fhName);




    /**
     * 根据栏舍 ID 查询栏舍信息及对应的栏圈信息
     * @param fenceHouseId 栏舍 ID
     * @return FenceHouseExt 对象
     */
    List<FenceHouseExt> findFenceHouseWithHurdles(String fenceHouseId);



    // 根据名称查询栏舍数量，排除当前栏舍
//    Integer countByName(@Param("fhName") String fhName,@Param("fhId") String fhId);
    FenceHouse countByName(@Param("fhName") String fhName);



    void deleteByIds(List<String> ids);
}
