package com.briup.product_source.bean.basic;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder //创建对象的一种方式
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private Integer logId;

    private String logUsername;

    private String logHost;

    private String logRealname;

    private String logRequestMethod;

    private String logRequestUri;

    private Date logTime;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogUsername() {
        return logUsername;
    }

    public void setLogUsername(String logUsername) {
        this.logUsername = logUsername;
    }

    public String getLogHost() {
        return logHost;
    }

    public void setLogHost(String logHost) {
        this.logHost = logHost;
    }

    public String getLogRealname() {
        return logRealname;
    }

    public void setLogRealname(String logRealname) {
        this.logRealname = logRealname;
    }

    public String getLogRequestMethod() {
        return logRequestMethod;
    }

    public void setLogRequestMethod(String logRequestMethod) {
        this.logRequestMethod = logRequestMethod;
    }

    public String getLogRequestUri() {
        return logRequestUri;
    }

    public void setLogRequestUri(String logRequestUri) {
        this.logRequestUri = logRequestUri;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}