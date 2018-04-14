package com.taotao.manage.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUiResult;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
/**
 * @author 芳芳
 * @create 2018-03-07 14:08
 * @desc
 **/
@RequestMapping("/item")
@Controller
public class ItemControl {
    @Autowired
    private ItemService itemService;
    /**
     * 添加商品
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveItem(Item item, @RequestParam(value ="desc" ) String desc){
        try {
             if(StringUtils.isEmpty(item.getTitle())){
                 return  new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
             }
             itemService.saveItemAndItemDesc(item,desc);
             return  new ResponseEntity<Void>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new  ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 查询   入参   当前页 page    页面大小rows      返回总记录数total  本页数据rows
     */
    @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<EasyUiResult> queryItemList(@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "rows",defaultValue = "30") Integer rows){
        try{
            PageInfo<Item> pageInfo = itemService.queryPageListAndSort("updated  DESC", page, rows, Item.class);
            if(pageInfo==null || pageInfo.getSize()==0){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            EasyUiResult easyUiResult = new EasyUiResult();
            easyUiResult.setTotal(pageInfo.getTotal());
            easyUiResult.setRows(pageInfo.getList());
            return  ResponseEntity.ok(easyUiResult);
        }catch(Exception e ){
            e.printStackTrace();
            //异常返回500
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
   }
    /**
     * 更新
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> queryItemList( Item item,String desc){
        try{
            if (item.getId()==null){
                return  new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            boolean flag = itemService.updateItemAndDesc(item, desc);
            if (flag){
                return  new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e ){
            e.printStackTrace();
            //异常返回500
        }
        return   new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /***
     * 删除
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteItemByIds(@RequestParam(value = "ids") Long[] ids){
        try{
            if (ids==null){
                return  new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            ArrayList<Object> list = new ArrayList<>();
            for (Long id:ids){
                list.add(id);
            }
            Integer account = itemService.deleteByIds("id", list, Item.class);
            if (account>0){
                return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }catch(Exception e ){
            e.printStackTrace();
        }
        return   new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
