package com.zjc.goods.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_spec_item[zjc_spec_item]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-14 09:18:10
 */
public class ZjcSpecItemPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 规格项id
	 */
	private Integer id;
	
	/**
	 * 规格id
	 */
	private Integer spec_id;
	
	/**
	 * 规格项
	 */
	private String item;
	
	
	private String name;
	
	private Integer type_id;

	/**
	 * 规格项id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 规格id
	 * 
	 * @return spec_id
	 */
	public Integer getSpec_id() {
		return spec_id;
	}
	
	/**
	 * 规格项
	 * 
	 * @return item
	 */
	public String getItem() {
		return item;
	}
	

	/**
	 * 规格项id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 规格id
	 * 
	 * @param spec_id
	 */
	public void setSpec_id(Integer spec_id) {
		this.spec_id = spec_id;
	}
	
	/**
	 * 规格项
	 * 
	 * @param item
	 */
	public void setItem(String item) {
		this.item = item;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	
}