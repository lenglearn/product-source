package com.briup.product_source.bean.ext;

import com.briup.product_source.bean.basic.Animal;
import com.briup.product_source.bean.basic.Batch;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ManagerAnimalExt extends Animal {
    //@JsonProperty("managerBatch")有对应前端json格式的名字
    private Batch managerBatch;  //内部可以不用这个名字
    private String managerHurdles;
    private String managerFenceHouse;

    public Batch getBatch() {
        return managerBatch;
    }

    public void setBatch(Batch managerBatch) {
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
