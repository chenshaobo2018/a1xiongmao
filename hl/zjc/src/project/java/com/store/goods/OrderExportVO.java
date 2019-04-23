/**
 * 
 */
package com.store.goods;

import aos.framework.core.typewrap.PO;

/**
 * @author Administrator
 *
 */
public class OrderExportVO extends PO{
	private static final long serialVersionUID = 1L;
	
	private String order_sn;
	
	private String user_id;
	
	private String consignee;
	
	private String goods_name;
	
	private String barter_coupons;
	
	private String cash;
	
	private String pay_status;
	
	private String shipping_status;
	
	private String add_time;
	
	private String shipping_time;
	
	private String confirm_time;
	
	private String goods_num;
	
	private String address;
	
	public String getGoods_num() {
		return goods_num;
	}

	public void setGoods_num(String goods_num) {
		this.goods_num = goods_num;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	private String mobile;

	public String getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getBarter_coupons() {
		return barter_coupons;
	}

	public void setBarter_coupons(String barter_coupons) {
		this.barter_coupons = barter_coupons;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

	public String getShipping_status() {
		return shipping_status;
	}

	public void setShipping_status(String shipping_status) {
		this.shipping_status = shipping_status;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public String getShipping_time() {
		return shipping_time;
	}

	public void setShipping_time(String shipping_time) {
		this.shipping_time = shipping_time;
	}

	public String getConfirm_time() {
		return confirm_time;
	}

	public void setConfirm_time(String confirm_time) {
		this.confirm_time = confirm_time;
	}
}
