package com.api.hl.vo;

import com.api.hl.dao.po.TOrderDetailedPO;


public class T_order_detailedVO extends TOrderDetailedPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7729967997275323612L;
	
	private String materialname;
	
	private String type_name;
	
	private String brand;
	
	private String specifications_name;
	
	private String note;

	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSpecifications_name() {
		return specifications_name;
	}

	public void setSpecifications_name(String specifications_name) {
		this.specifications_name = specifications_name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	

}
