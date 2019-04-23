/**
 * 
 */
package com.zjc.order.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * @author Administrator
 *
 */
public class ZjcOrderExportVO extends PO {
	
	private static final long serialVersionUID = 1L;
	private String v_oid;
	private String seller_id;
	private String order_sn;
	private String store_name;
	private String seller_mobile;
	private String user_id;
	private String consignee;
	private String contract_mobile;
	private String cash;
	private String barter_coupons;
	private String order_status;
	private String pay_status;
	private String pay_name;
	private String shipping_status;
	private String add_time;
	private String shipping_time;
	private String confirm_time;
	private String goods_name;
	private String goods_num;
	private String is_car;
	private String address;
	
	public String getV_oid() {
		return v_oid;
	}
	public void setV_oid(String v_oid) {
		this.v_oid = v_oid;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getSeller_mobile() {
		return seller_mobile;
	}
	public void setSeller_mobile(String seller_mobile) {
		this.seller_mobile = seller_mobile;
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
	public String getContract_mobile() {
		return contract_mobile;
	}
	public void setContract_mobile(String contract_mobile) {
		this.contract_mobile = contract_mobile;
	}
	public String getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash;
	}
	public String getBarter_coupons() {
		return barter_coupons;
	}
	public void setBarter_coupons(String barter_coupons) {
		this.barter_coupons = barter_coupons;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public String getPay_name() {
		return pay_name;
	}
	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
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
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(String goods_num) {
		this.goods_num = goods_num;
	}
	public String getIs_car() {
		return is_car;
	}
	public void setIs_car(String is_car) {
		this.is_car = is_car;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
