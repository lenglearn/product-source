package com.briup.product_source;

import com.briup.product_source.bean.basic.FenceHouse;
import com.briup.product_source.bean.ext.FenceHouseExt;
import com.briup.product_source.service.impl.AnalysisServiceImpl;
import com.briup.product_source.service.impl.FenceHouseServiceImpl;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ProductSourceApplicationTests {
    @Autowired
    private AnalysisServiceImpl analysisService;
    @Autowired
    private FenceHouseServiceImpl fenceHouseService;

    @Test
    void contextLoads() {
        PageInfo<FenceHouse> result = fenceHouseService.findByPage(1, 2, "舍");
        System.out.println(result);
    }

}
