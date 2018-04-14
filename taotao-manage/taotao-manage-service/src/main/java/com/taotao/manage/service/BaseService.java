package com.taotao.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.BasePojo;
/**
 * @author 芳芳
 * @create 2018-03-24 14:08
 * @desc
 **/
public class BaseService<T extends BasePojo> {
//	public abstract Mapper<T> getMapper();
	//这里根据spring4中支持的泛型注入  可以确定泛型T
	@Autowired
	public Mapper<T> mapper;
	/**
	 * 根据主键查询单个对象
	 * @param id
	 * @return
	 */
	public T queryById(Object id){
		return this.mapper.selectByPrimaryKey(id);
	}
	/**
	 * 根据条件查询多个对象
	 * @param record
	 * @return List
	 */
	public List<T> queryListByRecord(T record) {
		
		return this.mapper.select(record);
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<T> queryAll(){
		return this.mapper.select(null);
	}
	
	/**
	 * 根据条件查询单个对象
	 * 注意： 条件至多只有一个匹配记录
	 * @param record
	 * @return
	 */
	public T queryOne(T record){
		
		return this.mapper.selectOne(record);
	}
	/**   ******************************************************
	 * 使用mybatis分页助手  分页查询
	 * @param pageNum 页码
	 * @param pageSize 每页显示条数
	 * @param record 条件对象
	 * @return
	 */
	public PageInfo<T> queryPageListByWhere(Integer pageNum,Integer pageSize,T record){
		//启用分页助手
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = this.mapper.select(record);
		PageInfo<T> pageInfo = new PageInfo<T>(list);
		return pageInfo;
	}

	/**分页查询  但是传入条件
	 * 新增根据排序进行查询
	 */
	public  PageInfo<T> queryPageListAndSort(String sort,Integer pageNum,Integer pageSize,Class clazz){
		//启用分页助手
		PageHelper.startPage(pageNum, pageSize);
		Example example=new Example(clazz);
		example.setOrderByClause(sort);
		List<T> list = this.mapper.selectByExample(example);
		PageInfo<T> pageInfo = new PageInfo<T>(list);
		return pageInfo;

	}

	/**
	 * 新增单个对象
	 * @param record
	 * @return
	 */
	public Boolean save(T record){
		record.setCreated(new Date());
		record.setUpdated(record.getCreated());
		return this.mapper.insertSelective(record) == 1;
	}
	/**
	 * 根据主键id更新对象
	 * @param record
	 * @return
	 */
	public Boolean updateByPK(T record){
		record.setUpdated(new Date());
		return this.mapper.updateByPrimaryKeySelective(record) == 1;
	}
	/**
	 * 根据主键id删除单个对象
	 * @param id
	 * @return
	 */
	public Boolean deleteById(Object id){
		return this.mapper.deleteByPrimaryKey(id) == 1;
	}
	/**
	 * 根据属性执行 in删除
	 * @param property 属性名
	 * @param ids 参数集合
	 * @param clazz 删除对象的class
	 */
	@SuppressWarnings("rawtypes")
	public Integer deleteByIds(String property , List<Object> ids,Class clazz){
		Example example = new Example(clazz);
		Criteria criteria = example.createCriteria();
		criteria.andIn(property, ids);
		return this.mapper.deleteByExample(example);
	}
	
	/**
	 * 根据条件删除对象
	 * @param record
	 * @return
	 */
	public Integer deleteByWhrere(T record){
		return this.mapper.delete(record);
	}
}
