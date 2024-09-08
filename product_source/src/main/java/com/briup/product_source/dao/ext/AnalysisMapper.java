package com.briup.product_source.dao.ext;

import org.apache.ibatis.annotations.MapKey;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface AnalysisMapper {
    /**
     * 使用集合保存统计信息的中数值信息
     * @return
     */
    List<Integer> countByList();

    /**
     *
     * @return
     * 返回值数据格式：{栏舍={name=栏舍,num=3},....,....}
     */
    @MapKey("name") //将name列值作为key值使用 返回多条数据
    Map<String,Map<String,Object>> countByMap();

    /**
     *
     * @return
     * 返回数据格式： [{num=3, name=栏舍},{},{}]
     */
    List<Map<String,Object>> countByMapAndList();


    /*
    如果查询的结果只有一条数据map（key=xxx ,value=xxx）
         id  name age
         1   tom   18

         map= {id=1,name=tom,age=18}
         id  name age
         1   tom   18
         2   jack  20
         执行错误；提供 but  foud 2 one or null
         @Mapkey("id")
         map={1={id=1,name=tom,age=18},2={id=2,name=jack,age=20}}

     */
    Map<String, BigDecimal> countWeightByMap();
    /*
        如果查询的结果是一条获取多条封装到指定集合对象。每个元素就是map
        id  name age
         1   tom   18
         2   jack  20

         list<Map> = [{id=1,name=tom,age=18},{id=2,name=jack,age=20}]
     */
    List<Map<String,BigDecimal>> countWeightByList();


    List<Map<String,Integer>> findCountByWeight();

    List<Map<String,Object>> countSales();
}
