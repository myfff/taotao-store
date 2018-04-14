package com.taotao.manage.controller;

import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @author 芳芳
 * @create 2018-04-07 14:08
 * @desc
 **/
@Controller
@RequestMapping("/item/desc/")
public class ItemDescControl {
    @Autowired
    private ItemDescService itemDescService;

    //占位符的映射  在url中的映射url/{itemId}   还有就是url?name=namevalue 此种映射
    @RequestMapping(value ="{itemId}",method= RequestMethod.GET)
    public ResponseEntity<ItemDesc> queryItemDesc(@PathVariable(value ="itemId") Long  itemId){
        try {
            ItemDesc itemDesc = itemDescService.queryById(itemId);
            if(itemDesc==null){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return  ResponseEntity.status(HttpStatus.OK).body(itemDesc);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}



