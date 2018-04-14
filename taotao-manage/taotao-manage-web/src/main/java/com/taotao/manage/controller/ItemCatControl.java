package com.taotao.manage.controller;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 芳芳
 * @create 2018-03-06 14:08
 * @desc
 **/
@RequestMapping("item/cat")
@Controller
public class ItemCatControl {
    @Autowired
    private ItemCatService itemCatService;
    @RequestMapping(method = RequestMethod.GET)
    //tree组件的id会自动添加进去
    public ResponseEntity<List<ItemCat>>  queryItemCat(@RequestParam(value = "id",defaultValue = "0")Long parentId) {
        try {
            ItemCat recoder=new ItemCat();
            recoder.setParentId(parentId);
            recoder.setStatus(1);
            List<ItemCat> itemCats = itemCatService.queryListByRecord(recoder);
            if (itemCats == null || itemCats.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        return  ResponseEntity.status(HttpStatus.OK).body(itemCats);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    /** 根据cid查询做数据的回显
     * @param cid
     * @return
     */
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    //tree组件的id会自动添加进去
    public ResponseEntity<ItemCat>  queryItemCatById(@PathVariable("id")Long cid) {
        try {
            ItemCat itemCat = itemCatService.queryById(cid);
            if (itemCat == null ) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return  ResponseEntity.status(HttpStatus.OK).body(itemCat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }



}
