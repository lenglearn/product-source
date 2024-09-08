package com.briup.product_source.service;

import com.briup.product_source.bean.basic.FenceHouse;
import com.briup.product_source.bean.ext.FenceHouseExt;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FenceHouseService {
    /**
     * 多条件分页查询
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param fhName   栏舍名称
     * @return
     */
    PageInfo<FenceHouse> findByPage(int pageNum, int pageSize, String fhName);
    /**
     * 根据id查询栏舍信息及其对应的栏圈信息
     *
     * @param id 栏舍id
     * @return FenceHouseExt类型是一个自定义类型，用来实现1对多的映射。思考如何设计这个类？
     */
    FenceHouseExt findById(String id);
    /**
     * 保存或者更新
     * @param FenceHouse 栏舍信息对象
     */
    void saveOrUpdate(FenceHouse fenceHouse);
    /**
     * 通过id删除
     * @param id  栏舍id
     */
    void deleteById(String id);
    /**
     * 批量删除栏舍信息
     * @param ids
     */
    void deleteByBatch(List<String> ids) throws Exception;
}
