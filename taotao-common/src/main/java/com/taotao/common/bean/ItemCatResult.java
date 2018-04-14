package com.taotao.common.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 芳芳
 * @create 2018-04-09 12:26
 * @desc 前台商品类目的结果查询
 **/
public class ItemCatResult {
    //转化为json时以data为key值
    @JsonProperty("data")
    private List<ItemCatData> itemCatDatas=new ArrayList<ItemCatData>();

    public List<ItemCatData> getItemCatDatas() {
        return itemCatDatas;
    }

    public void setItemCatDatas(List<ItemCatData> itemCatDatas) {
        this.itemCatDatas = itemCatDatas;
    }
}



