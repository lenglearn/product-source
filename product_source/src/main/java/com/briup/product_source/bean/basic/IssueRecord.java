package com.briup.product_source.bean.basic;

import java.util.Date;

public class IssueRecord {
    private Integer isrId;

    private Date isrTime;

    private Integer isrNum;

    private Double isrPrice;

    private Date isrDeliver;

    private String isrStatus;

    private Integer isrCustomerId;

    public Integer getIsrId() {
        return isrId;
    }

    public void setIsrId(Integer isrId) {
        this.isrId = isrId;
    }

    public Date getIsrTime() {
        return isrTime;
    }

    public void setIsrTime(Date isrTime) {
        this.isrTime = isrTime;
    }

    public Integer getIsrNum() {
        return isrNum;
    }

    public void setIsrNum(Integer isrNum) {
        this.isrNum = isrNum;
    }

    public Double getIsrPrice() {
        return isrPrice;
    }

    public void setIsrPrice(Double isrPrice) {
        this.isrPrice = isrPrice;
    }

    public Date getIsrDeliver() {
        return isrDeliver;
    }

    public void setIsrDeliver(Date isrDeliver) {
        this.isrDeliver = isrDeliver;
    }

    public String getIsrStatus() {
        return isrStatus;
    }

    public void setIsrStatus(String isrStatus) {
        this.isrStatus = isrStatus;
    }

    public Integer getIsrCustomerId() {
        return isrCustomerId;
    }

    public void setIsrCustomerId(Integer isrCustomerId) {
        this.isrCustomerId = isrCustomerId;
    }
}