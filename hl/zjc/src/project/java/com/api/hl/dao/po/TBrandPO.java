package com.api.hl.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>t_brand[t_brand]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-03-05 10:01:08
 */
public class TBrandPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * brand_name
	 */
	private String brand_name;
	
	/**
	 * supplier_id
	 */
	private String supplier_id;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * brand_name
	 * 
	 * @return brand_name
	 */
	public String getBrand_name() {
		return brand_name;
	}
	
	/**
	 * supplier_id
	 * 
	 * @return supplier_id
	 */
	public String getSupplier_id() {
		return supplier_id;
	}
	

	/**
	 * id
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * brand_name
	 * 
	 * @param brand_name
	 */
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	
	/**
	 * supplier_id
	 * 
	 * @param supplier_id
	 */
	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}
	

}