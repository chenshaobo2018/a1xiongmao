package com.api.hl.vo;

import java.util.Date;

import com.api.hl.dao.po.TOrderDetailedPO;


/**
 * 收货统计VO对象
 * @author lenovo
 *
 */
public class T_inPutGoodsjVO extends TOrderDetailedPO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8127639395683195932L;

	
	private String type_name;
	
	private String brand;
	
	private String specifications_name;
	
	private String note;
	
	private String user_name;
	
	private String project_name;
	
	private String suppliername;
	
	private Date add_date;
	
	private String price;
	
	private String specount;
	
	private String totalnum;
	
	private String sumprice;
	
	private String receive_time;
	

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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSpecount() {
		return specount;
	}

	public void setSpecount(String specount) {
		this.specount = specount;
	}

	public String getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(String totalnum) {
		this.totalnum = totalnum;
	}

	public String getSumprice() {
		return sumprice;
	}

	public void setSumprice(String sumprice) {
		this.sumprice = sumprice;
	}

	public String getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}
	

}
