package com.briup.product_source.bean.basic;

import java.util.Date;

public class Hurdles {
    private String hId;

    private String hName;

    private String hDesc;

    private Integer hMax;

    private Integer hSaved;

    private Date hTime;

    private String hEnable;

    private String hFull;

    private String hFenceId;

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethDesc() {
        return hDesc;
    }

    public void sethDesc(String hDesc) {
        this.hDesc = hDesc;
    }

    public Integer gethMax() {
        return hMax;
    }

    public void sethMax(Integer hMax) {
        this.hMax = hMax;
    }

    public Integer gethSaved() {
        return hSaved;
    }

    public void sethSaved(Integer hSaved) {
        this.hSaved = hSaved;
    }

    public Date gethTime() {
        return hTime;
    }

    public void sethTime(Date hTime) {
        this.hTime = hTime;
    }

    public String gethEnable() {
        return hEnable;
    }

    public void sethEnable(String hEnable) {
        this.hEnable = hEnable;
    }

    public String gethFull() {
        return hFull;
    }

    public void sethFull(String hFull) {
        this.hFull = hFull;
    }

    public String gethFenceId() {
        return hFenceId;
    }

    public void sethFenceId(String hFenceId) {
        this.hFenceId = hFenceId;
    }
}