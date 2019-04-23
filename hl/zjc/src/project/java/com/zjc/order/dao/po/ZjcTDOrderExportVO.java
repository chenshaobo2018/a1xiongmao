/**
 * 
 */
package com.zjc.order.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * @author Administrator
 *
 */
public class ZjcTDOrderExportVO extends PO {
	
	private static final long serialVersionUID = 1L;
	private String order_sn;
	private String user_id;
	private String consignee;
	private String cash;
	private String barter_coupons;
	private String add_time;
	private String goods_name;

	/**
	 * 催单次数
	 */
	private String reminder;
	
	/**
	 * 上次催单时间
	 */
	private String reminderTime;
	
	/**
	 * 退单状态：0发起退单1退单中2退单成功3退单失败
	 */
	private String single_back;
	
	/**
	 * 退单时间
	 */
	private String single_back_time;
	
	/**
	 * 确认退单时间
	 */
	private String confirm_single_back_time;
	
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
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getReminder() {
		return reminder;
	}
	public void setReminder(String reminder) {
		this.reminder = reminder;
	}
	public String getReminderTime() {
		return reminderTime;
	}
	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}
	public String getSingle_back() {
		return single_back;
	}
	public void setSingle_back(String single_back) {
		this.single_back = single_back;
	}
	public String getSingle_back_time() {
		return single_back_time;
	}
	public void setSingle_back_time(String single_back_time) {
		this.single_back_time = single_back_time;
	}
	public String getConfirm_single_back_time() {
		return confirm_single_back_time;
	}
	public void setConfirm_single_back_time(String confirm_single_back_time) {
		this.confirm_single_back_time = confirm_single_back_time;
	}
	
}
