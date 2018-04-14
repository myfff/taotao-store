package com.taotao.common.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 芳芳
 * @create 2018-04-09 12:28
 * @desc 前台商品 左侧分类结果封装
 **/
public class ItemCatData {
    //序列化为json是以u作为key值
    @JsonProperty("u")
    private  String url;
    @JsonProperty("n")
    private  String name;
    @JsonProperty("i")
    private  List<?> items ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }
}



