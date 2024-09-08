package com.briup.product_source.bean.basic;

import java.util.Date;

public class DiseaseRecord {
    private Integer drId;

    private String drAnimalId;

    private String drDesc;

    private String drCure;

    private Date drTime;

    private String drStatus;

    private Integer drDId;

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    public String getDrAnimalId() {
        return drAnimalId;
    }

    public void setDrAnimalId(String drAnimalId) {
        this.drAnimalId = drAnimalId;
    }

    public String getDrDesc() {
        return drDesc;
    }

    public void setDrDesc(String drDesc) {
        this.drDesc = drDesc;
    }

    public String getDrCure() {
        return drCure;
    }

    public void setDrCure(String drCure) {
        this.drCure = drCure;
    }

    public Date getDrTime() {
        return drTime;
    }

    public void setDrTime(Date drTime) {
        this.drTime = drTime;
    }

    public String getDrStatus() {
        return drStatus;
    }

    public void setDrStatus(String drStatus) {
        this.drStatus = drStatus;
    }

    public Integer getDrDId() {
        return drDId;
    }

    public void setDrDId(Integer drDId) {
        this.drDId = drDId;
    }
}