/**
 * 
 */
package com.zjc.goods.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * @author Administrator
 *
 */
public class ZjcGoodsSpecVO extends PO{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 规格项编号
	 */
	private Integer spec_id;
	
	/**
	 * 规格项名称
	 */
	private String spec_name;
	
	/**
	 * 规格值编号
	 */
	private Integer item_id;
	
	/**
	 * 规格值名称
	 */
	private String item_name;

	public Integer getSpec_id() {
		return spec_id;
	}

	public void setSpec_id(Integer spec_id) {
		this.spec_id = spec_id;
	}

	public String getSpec_name() {
		return spec_name;
	}

	public void setSpec_name(String spec_name) {
		this.spec_name = spec_name;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
}
