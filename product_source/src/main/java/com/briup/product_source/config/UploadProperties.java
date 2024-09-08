package com.briup.product_source.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "file.upload")
public class UploadProperties {
    private String dir;
    private String ip;
    private String port;
    private String url;
    public String getBaseUrl(){
        StringBuilder sb = new StringBuilder();
        return sb.append("http://")
                .append(ip)
                .append(":")
                .append(port)
                .append(url)
                .append("/").toString();
    }
}
