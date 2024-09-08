package com.briup.product_source;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan
@EnableSwagger2Doc
@MapperScan("com.briup.product_source.dao")
public class ProductSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductSourceApplication.class, args);
    }

}
