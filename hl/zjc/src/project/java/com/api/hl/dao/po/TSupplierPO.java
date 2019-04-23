package com.api.hl.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>t_supplier[t_supplier]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-03-05 10:01:11
 */
public class TSupplierPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * supplier_name
	 */
	private String supplier_name;
	
	/**
	 * phones
	 */
	private String phones;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * supplier_name
	 * 
	 * @return supplier_name
	 */
	public String getSupplier_name() {
		return supplier_name;
	}
	
	/**
	 * phones
	 * 
	 * @return phones
	 */
	public String getPhones() {
		return phones;
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
	 * supplier_name
	 * 
	 * @param supplier_name
	 */
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	
	/**
	 * phones
	 * 
	 * @param phones
	 */
	public void setPhones(String phones) {
		this.phones = phones;
	}
	

}