package com.taotao.manage.service;

import com.taotao.common.bean.ItemCatData;
import com.taotao.common.bean.ItemCatResult;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import org.omg.CORBA.ORBPackage.InconsistentTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ItemCatMapper;
import com.taotao.manage.pojo.ItemCat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 芳芳
 * @create 2018-04-07 14:08
 * @desc
 **/
@Service
public class ItemCatService extends BaseService<ItemCat>{

//	@Override
//	public Mapper<ItemCat> getMapper() {
//		return this.itemCatMapper;
//	}
    @Autowired
	private ItemCatMapper itemCatMapper;

    /**
     *
     * 后台系统对外提供接口
     * 要求返回的json格式数据如下：
     *   {"data":[
     {"u":"/products/1.html",
     "n":"<a href='/products/1.html'>图书、音像、电子书刊</a>",
     "i":[{
     "u":"/products/2.html",
     "n":"电子书刊",
     "i":["/products/3.html|电子书",
     "/products/4.html|网络原创",
     "/products/5.html|数字杂志",
     "/products/6.html|多媒体图书"]},
     {"u":"/products/7.html","n":"音像","i":["
     * @return
     *list-->map-->list>
     */

    public ItemCatResult queryAllToTree() {
        //查询出所有商品类目数据
        List<ItemCat> allItemCat = super.queryAll();

        //希望将数据整理成 一个Map<父类目id,对应的子类目集合>
        Map<Long,List<ItemCat>> map = new HashMap<Long,List<ItemCat>>();
        for(ItemCat itemCat : allItemCat){
            //希望知道该对象的父类目是否已经存在于map中，如果不存在，加入进去
            if(!map.containsKey(itemCat.getParentId())){
                //可以先new一个   放进去  引用类型可以
                map.put(itemCat.getParentId(), new ArrayList<ItemCat>());
            }
            List<ItemCat> list = map.get(itemCat.getParentId());
            list.add(itemCat);
        }

        ItemCatResult itemCatResult = new ItemCatResult();
        //封装一级类目的集合
        List<ItemCatData> itemCatListData1 = new ArrayList<ItemCatData>();
        itemCatResult.setItemCatDatas(itemCatListData1);
        List<ItemCat> itemCats1 = map.get(0L);
        //此循坏结束要遍历并封装所有的数据
        for(ItemCat itemCat1:itemCats1){
            ItemCatData itemCatData1 = new ItemCatData();
            itemCatListData1.add(itemCatData1);
            itemCatData1.setUrl("/products/"+itemCat1.getId()+".html");
            itemCatData1.setName("<a href='/products/"+itemCat1.getId()+".html'>"+itemCat1.getName()+"</a>");


            //二级类目
            //封装2级类目的集合
            List<ItemCatData> itemCatsListData2= new ArrayList<ItemCatData>();
            itemCatData1.setItems(itemCatsListData2);
            List<ItemCat> itemCats2=map.get(itemCat1.getId());
            for(ItemCat itemCat2:itemCats2){
                ItemCatData itemCatData2 = new ItemCatData();
                itemCatsListData2.add(itemCatData2);
                itemCatData2.setUrl("/products/"+itemCat2.getId()+".html");
                itemCatData2.setName(itemCat2.getName());

                //三级类目
                List<String> itemCatsListData3=new ArrayList<String>();
                itemCatData2.setItems(itemCatsListData3);
                List<ItemCat>  itemCats3=map.get(itemCat2.getId());
                for (ItemCat itemCat3:itemCats3){
                    itemCatsListData3.add("/products/" + itemCat3.getId() + ".html|" + itemCat3.getName());
                }
            }
        }
       return itemCatResult;
    }

}
