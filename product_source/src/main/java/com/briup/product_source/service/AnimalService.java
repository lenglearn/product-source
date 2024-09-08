package com.briup.product_source.service;

import com.briup.product_source.bean.basic.Animal;
import com.briup.product_source.bean.ext.AnimalExt;
import com.briup.product_source.bean.ext.ManagerAnimalExt;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AnimalService {
    /**
     * 新增或修改动物信息
     * @param animal 动物信息
     */
    void saveOrUpdate(Animal animal);
    /**
     * 通过id删除动物信息
     * @param id 编号
     */
    void deleteById(String id);
    /**
     * 通过id批量删除动物信息
     * @param ids 编号
     */
    void deleteByBatch(List<String> ids);
    /**
     * 多条件分页查询
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @param aHealthy 健康状态
     * @param aGender 性别
     * @return 动物及其关联信息的分页信息
     */
    PageInfo<AnimalExt> findByPage(Integer pageNum, int pageSize, String aHealthy, String aGender);
    /**
     * 根据动物id获取动物及其关联信息
     * @param id 动物编号
     * @return 动物及其关联信息
     */
    ManagerAnimalExt findById(String id);
    /**
     * 根据动物编号生成对应的动物信息溯源二维码图片将二维码保存到指定目录下，并将图片路径存储在数据库表中
     * @param id 动物编号
     * @return  生成二维码图片地址
     */
    String createQRcodeByAnimalId(String id);
}
