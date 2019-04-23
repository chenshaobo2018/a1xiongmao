package com.zjc.goods.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_goods_type[zjc_goods_type]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-05-31 16:57:16
 */
public class ZjcGoodsTypePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id自增
	 */
	private Integer id;
	
	/**
	 * 类型名称
	 */
	private String name;
	

	/**
	 * id自增
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 类型名称
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	

	/**
	 * id自增
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 类型名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	

}