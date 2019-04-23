package com.zjc.goods.dao.po;


import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_spec[zjc_spec]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-14 09:18:09
 */
public class ZjcSpecVO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 规格表
	 */
	private Integer id;
	
	
	/**
	 * 规格类型名称
	 */
	private String type_name;
	
	/**
	 * 规格项
	 */
	private String item_name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	
}