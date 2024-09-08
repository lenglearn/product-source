package com.briup.product_source.service.impl;

import com.briup.product_source.dao.basic.*;
import com.briup.product_source.dao.ext.*;
import com.briup.product_source.service.AnalysisService;
import com.briup.product_source.util.Result;
import com.google.common.collect.ImmutableMap;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private AnalysisMapper analysisMapper;
    @Override
    public Map<String, List<Object>> countNum() {
        Map<String, Map<String, Object>> map = analysisMapper.countByMap();
        //获取统计类型
        Set<String> names = map.keySet();
        //获取统计数据 [{name=栏舍,num=3},{},{}]-----> [3,10,20,5]
        //内部迭代元素的对象,将map元素转化为Integer元素
        //m={name=栏舍,num=3}  ==>map方法 返回=3
        List<Object> values = map.values()
                .stream()
                .map(m -> m.get("num"))
                .collect(Collectors.toList());
        Map result = new HashMap<>();
        result.put("name",names);
        result.put("value",values);
        return result;
    }

    @Override
    public Map<String, Integer> countWeight() {
        Map<String, Integer> resultMap = new HashMap<>();
        for (Map<String,Integer> map : analysisMapper.findCountByWeight()) {
            String key = String.valueOf(map.get("weight_group"));
            Integer value = map.get("count");
            resultMap.put(key,value);
        }
        return resultMap;
    }

    @Override
    public Map<String, Integer> countDiseaseByName() {
        Map<String,Integer> resultMap = new HashMap<>();
        return null;
    }

    @Override
    public Map<String, List<Object>> countSales() {
        //[{month=4, num=4}...]
        List<Map<String,Object>> list = analysisMapper.countSales();
        List<String> names = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月",
                "7月", "8月","9月", "10月", "11月", "12月");
        //创建元素个数为12的list集合 每个元素都是0
        List values = new ArrayList(Collections.nCopies(12, 0));

        //将查询的数据添加到指定的集合中
        list.forEach(data ->{
            int month = (Integer) data.get("month");
            int num = ((BigDecimal) data.get("num")).intValue();
            values.set(month-1,num);
        });
        //返回结果集 google工具类使用
        Map result = ImmutableMap.of("name", names, "value", values);
        return result;
    }
}
