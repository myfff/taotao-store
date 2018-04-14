package com.taotao.common.bean;

import java.util.List;

/**  所有的分类数据
 * @author 芳芳
 * @create 2018-04-01 12:26
 * @desc 后台  商品查询时使用
 **/
public class EasyUiResult {

	//总记录数
	private Long total;
	//查询对象的集合
	private List<?> rows;
	
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public EasyUiResult() {
		super();
	}
	
	
}
