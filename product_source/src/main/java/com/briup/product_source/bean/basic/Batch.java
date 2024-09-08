package com.briup.product_source.bean.basic;

import java.util.Date;

public class Batch {
    private String bSerialId;

    private String bDesc;

    private String bQuarantine;

    private String bQualified;

    private Date bTime;

    public String getbSerialId() {
        return bSerialId;
    }

    public void setbSerialId(String bSerialId) {
        this.bSerialId = bSerialId;
    }

    public String getbDesc() {
        return bDesc;
    }

    public void setbDesc(String bDesc) {
        this.bDesc = bDesc;
    }

    public String getbQuarantine() {
        return bQuarantine;
    }

    public void setbQuarantine(String bQuarantine) {
        this.bQuarantine = bQuarantine;
    }

    public String getbQualified() {
        return bQualified;
    }

    public void setbQualified(String bQualified) {
        this.bQualified = bQualified;
    }

    public Date getbTime() {
        return bTime;
    }

    public void setbTime(Date bTime) {
        this.bTime = bTime;
    }
}