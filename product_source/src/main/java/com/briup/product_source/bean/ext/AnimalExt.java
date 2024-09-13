package com.briup.product_source.bean.ext;

import com.briup.product_source.bean.basic.Animal;
import com.briup.product_source.bean.basic.Batch;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AnimalExt extends Animal {
    private String managerHurdles;
    private String managerFenceHouse;
    @JsonProperty("aBackUp3")
    private String qrCodeUrl;
    private Batch managerBatch;

    public Batch getManagerBatch() {
        return managerBatch;
    }

    public void setManagerBatch(Batch managerBatch) {
        this.managerBatch = managerBatch;
    }

    public String getManagerHurdles() {
        return managerHurdles;
    }

    public void setManagerHurdles(String managerHurdles) {
        this.managerHurdles = managerHurdles;
    }

    public String getManagerFenceHouse() {
        return managerFenceHouse;
    }

    public void setManagerFenceHouse(String managerFenceHouse) {
        this.managerFenceHouse = managerFenceHouse;
    }
}
