package com.briup.product_source.web.controller;


import com.briup.product_source.config.UploadProperties;
import com.briup.product_source.util.OSSUtil;
import com.briup.product_source.util.Result;
import com.briup.product_source.util.ResultCode;
import com.briup.product_source.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Api(tags = "文件上传模块")
@RestController
@RequestMapping("/file")
public class FileController {
//    @Value("${upload.serverPath}")
//    private String serverPath;
//    @Autowired
//    private UploadProperties prop;
    @Autowired
    private OSSUtil ossUtil;

    @ApiOperation("文件上传")
    @PostMapping("upLoad")
    public Result<String> upLoad(@RequestPart MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        String filename = file.getOriginalFilename();
//        file.transferTo(new File(prop.getDir(),filename)); //存到本地测试用，nginx配置过在浏览器可以访问
        String newFilename = ossUtil.upload(filename, is);
        return ResultUtil.success(newFilename);
        //通过MultipartFile
//        String filename = file.getOriginalFilename();
//        long size = file.getSize();
//        log.info("文件名：{}  文件大小：{}",filename,size);
//
//        byte[] bytes = file.getBytes();
//        InputStream in = file.getInputStream();
//
//        String id = UUID.randomUUID().toString();
//        String newFileName = id + "-" + filename;
//        file.transferTo(new File(prop.getDir(),newFileName));
//        return prop.getBaseUrl() + newFileName;
    }

}

