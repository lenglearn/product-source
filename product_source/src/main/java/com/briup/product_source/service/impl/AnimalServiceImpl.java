package com.briup.product_source.service.impl;

import com.briup.product_source.bean.basic.Animal;
import com.briup.product_source.bean.ext.AnimalExt;
import com.briup.product_source.bean.ext.ManagerAnimalExt;
import com.briup.product_source.dao.basic.AnimalMapper;
import com.briup.product_source.dao.basic.HurdlesMapper;
import com.briup.product_source.dao.ext.AnimalExtMapper;
import com.briup.product_source.service.AnimalService;
import com.briup.product_source.util.BriupAssert;
import com.briup.product_source.util.OSSUtil;
import com.briup.product_source.util.ResultCode;
import com.briup.product_source.util.SnowflakeIdGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
    @Autowired
    private OSSUtil ossUtil;
    @Value("${QRcode.host}")
    private String QRcode;
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
        String text = QRcode + "/animal/findByAnimalId?animalId=" + id; // 要编码到二维码中的内容
        int width = 300; // 二维码图片宽度
        int height = 300; // 二维码图片高度
        String fileName = "qr.png"; // 生成的二维码图片路径
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG",out);
            InputStream in = new ByteArrayInputStream(out.toByteArray());
            String path = ossUtil.upload(fileName, in);
            return path;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}