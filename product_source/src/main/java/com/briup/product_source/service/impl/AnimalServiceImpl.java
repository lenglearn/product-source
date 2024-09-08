package com.briup.product_source.service.impl;

import com.briup.product_source.bean.basic.Animal;
import com.briup.product_source.bean.ext.AnimalExt;
import com.briup.product_source.bean.ext.ManagerAnimalExt;
import com.briup.product_source.dao.basic.AnimalMapper;
import com.briup.product_source.dao.basic.HurdlesMapper;
import com.briup.product_source.dao.ext.AnimalExtMapper;
import com.briup.product_source.service.AnimalService;
import com.briup.product_source.util.BriupAssert;
import com.briup.product_source.util.ResultCode;
import com.briup.product_source.util.SnowflakeIdGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

import static com.briup.product_source.util.ResultCode.ID_NOT_EXIST;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    private AnimalExtMapper animalExtMapper;
    @Autowired
    private AnimalMapper animalMapper;
    @Autowired
    private HurdlesMapper hurdlesMapper;
    @Override
    public void saveOrUpdate(Animal animal) {

        // 如果 ID 为空，则生成一个新的 ID
        if (!StringUtils.hasText(animal.getaAnimalId())) {
            BriupAssert.isTrue(hurdlesMapper.selectByPrimaryKey(animal.getaAnimalId()).gethSaved() <hurdlesMapper.selectByPrimaryKey(animal.getaAnimalId()).gethMax(), ResultCode.H_SAVED_FULL);
                // 使用雪花算法生成唯一 ID
                Long newId = SnowflakeIdGenerator.generateId();
                animal.setaAnimalId(String.valueOf(newId));
                animal.setaTime(new Date(System.currentTimeMillis()));
                // 执行插入操作
                animalMapper.insertSelective(animal);
        } else {
            // 如果 ID 不为空，检查数据库中是否存在该 ID
            BriupAssert.notNull(animalMapper.selectByPrimaryKey(animal.getaAnimalId()),ID_NOT_EXIST);
            // 执行更新操作
            animalMapper.updateByPrimaryKeySelective(animal);
        }
    }

    @Override
    public void deleteById(String id) {
        Animal a = animalExtMapper.selectByPrimaryKey(id);
        BriupAssert.notNull(a,ResultCode.DATA_NOT_EXIST);
        animalExtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByBatch(List<String> ids) {
        animalExtMapper.deleteByIds(ids);
    }

    @Override
    public PageInfo<AnimalExt> findByPage(Integer pageNum, int pageSize, String aHealthy, String aGender) {
        PageHelper.startPage(pageNum,pageSize,true);
        List<AnimalExt> list = animalExtMapper.findAnimals(aHealthy, aGender);
        return new PageInfo<>(list);
    }

    @Override
    public ManagerAnimalExt findById(String id) {
        ManagerAnimalExt result = animalExtMapper.findAnimalWithBatch(id);
        return result;
    }

    @Override
    public String createQRcodeByAnimalId(String id) {
        return null;
    }
}