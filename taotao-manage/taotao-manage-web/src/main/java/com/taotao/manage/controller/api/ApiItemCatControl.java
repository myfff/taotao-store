package com.taotao.manage.controller.api;

import com.taotao.common.bean.ItemCatResult;
import com.taotao.manage.service.ItemCatService;
import com.taotao.manage.service.PropertiesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author 芳芳
 * @create 2018-04-09 14:14
 * @desc 前台商品左侧分类数据请求的API
 **/
@Controller
@RequestMapping("/api/item/cat/all")
public class ApiItemCatControl {
    @Autowired
    private ItemCatService itemCatService;
    @Autowired
    private PropertiesService propertiesService;

    /**  api1  前台左侧分类查询   此种方法还没有同意jsonp，，我们需要统一jsop让其保证和后台一样
     * @param callback
     * @return
     */
 /*    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getAllItemCats(@RequestParam(value = "callback",required = false) String callback){
        try {
           ItemCatResult itemCatResults  =   itemCatService.queryAllToTree();
            String jsonResult = propertiesService.MAPPER.writeValueAsString(itemCatResults);
            if(!StringUtils.isEmpty(callback)){
                return  ResponseEntity.ok(callback+"("+jsonResult+")");    // 后台返回的对象是 回调名字+”(”+json对象+”)”
           }
           return  ResponseEntity.ok(jsonResult);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }*/


    /**
     * ajax的跨域请求  规范到我们以前写后台代码的风格   有两种方式
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ItemCatResult> getAllItemCats(){
        try {
            ItemCatResult itemCatResults  =   itemCatService.queryAllToTree();
            return   ResponseEntity.ok(itemCatResults);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}



