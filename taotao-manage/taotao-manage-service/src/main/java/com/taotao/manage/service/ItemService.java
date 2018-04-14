package com.taotao.manage.service;
import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import org.omg.CORBA.BAD_CONTEXT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author 芳芳
 * @create 2018-04-07 14:08
 * @desc
 **/
@Service
public class ItemService extends BaseService<Item>{
  @Autowired
    private ItemMapper itemMapper;
    /**
     * 保存商品和商品描述
     * 保证操作的原子性    所以是要在同一事物中
     */
    @Autowired
    private ItemDescService itemDescService;
    public void saveItemAndItemDesc(Item item,String desc){
        item.setStatus(1);//保证状态初始值为1
        item.setId(null);//让数据库自增
        super.save(item);

        ItemDesc itemDesc=new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        itemDescService.save(itemDesc);
    }

    public boolean updateItemAndDesc(Item item, String desc) {
        //强制不能修改的字段为null
        item.setStatus(null);
        Boolean flag = super.updateByPK(item);
        if(flag){
            ItemDesc itemDesc = new ItemDesc();
            itemDesc.setItemId(item.getId());
            itemDesc.setItemDesc(desc);
            flag = this.itemDescService.updateByPK(itemDesc);
        }
  return flag;
    }
}
