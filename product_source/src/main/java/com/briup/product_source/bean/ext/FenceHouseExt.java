package com.briup.product_source.bean.ext;

import com.briup.product_source.bean.basic.FenceHouse;
import com.briup.product_source.bean.basic.Hurdles;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FenceHouseExt extends FenceHouse {
    //定义属性表示1对多关系
    //解决对象属性名和json字符串的key值对应关系
    @JsonProperty("mhs") //对应的json字符串key值名称
    private List<Hurdles> hurdlesList;
    public List<Hurdles> getHurdles() {
        return hurdlesList;
    }

    public void setHurdles(List<Hurdles> hurdles) {
        this.hurdlesList = hurdles;
    }
}

