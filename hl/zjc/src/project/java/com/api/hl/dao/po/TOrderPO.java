package com.api.hl.dao.po;

import aos.framework.core.typewrap.PO;
import java.util.Date;

/**
 * <b>t_order[t_order]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-03-05 10:01:09
 */
public class TOrderPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * project_name
	 */
	private String project_name;
	
	/**
	 * user_name
	 */
	private String user_name;
	
	/**
	 * note
	 */
	private String note;
	
	/**
	 * add_date
	 */
	private Date add_date;
	
	/**
	 * user_id
	 */
	private String user_id;
	
	/**
	 * is_refer
	 */
	private String is_refer;
	
	/**
	 * status
	 */
	private String status;
	
	/**
	 * sum_price
	 */
	private String sum_price;
	
	/**
	 * adjust_price
	 */
	private String adjust_price;
	
	private String material_name;
	private String price;
	private String OrderDetailedSize;
	private String OrderDetailedCash;
	private String goods_id;//数据库有该字段，但是是我们手动添加的
	
	public String getOrderDetailedSize() {
		return OrderDetailedSize;
	}

	public void setOrderDetailedSize(String orderDetailedSize) {
		OrderDetailedSize = orderDetailedSize;
	}

	public String getOrderDetailedCash() {
		return OrderDetailedCash;
	}

	public void setOrderDetailedCash(String orderDetailedCash) {
		OrderDetailedCash = orderDetailedCash;
	}

	public String getMaterial_name() {
		return material_name;
	}

	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * project_name
	 * 
	 * @return project_name
	 */
	public String getProject_name() {
		return project_name;
	}
	
	/**
	 * user_name
	 * 
	 * @return user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	
	/**
	 * note
	 * 
	 * @return note
	 */
	public String getNote() {
		return note;
	}
	
	/**
	 * add_date
	 * 
	 * @return add_date
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * user_id
	 * 
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	
	/**
	 * is_refer
	 * 
	 * @return is_refer
	 */
	public String getIs_refer() {
		return is_refer;
	}
	
	/**
	 * status
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * sum_price
	 * 
	 * @return sum_price
	 */
	public String getSum_price() {
		return sum_price;
	}
	
	/**
	 * adjust_price
	 * 
	 * @return adjust_price
	 */
	public String getAdjust_price() {
		return adjust_price;
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
	 * project_name
	 * 
	 * @param project_name
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	/**
	 * user_name
	 * 
	 * @param user_name
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/**
	 * note
	 * 
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	/**
	 * add_date
	 * 
	 * @param add_date
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * user_id
	 * 
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * is_refer
	 * 
	 * @param is_refer
	 */
	public void setIs_refer(String is_refer) {
		this.is_refer = is_refer;
	}
	
	/**
	 * status
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * sum_price
	 * 
	 * @param sum_price
	 */
	public void setSum_price(String sum_price) {
		this.sum_price = sum_price;
	}
	
	/**
	 * adjust_price
	 * 
	 * @param adjust_price
	 */
	public void setAdjust_price(String adjust_price) {
		this.adjust_price = adjust_price;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	
	
}