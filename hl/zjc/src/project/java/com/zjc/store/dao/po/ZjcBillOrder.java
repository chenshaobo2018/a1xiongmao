/**
 * 
 */
package com.zjc.store.dao.po;

import java.math.BigDecimal;

/**
 * @author Administrator
 *
 */
public class ZjcBillOrder {
	/**
	 * 订单号
	 */
	private String order_sn;
	
	/**
	 * 订单完成时间
	 */
	private String confirm_time;
	
	/**
	 * 订单支付方式
	 */
	private String pay_name;
	
	/**
	 * 订单金额
	 */
	private BigDecimal cash;

	public String getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}

	public String getConfirm_time() {
		return confirm_time;
	}

	public void setConfirm_time(String confirm_time) {
		this.confirm_time = confirm_time;
	}

	public String getPay_name() {
		return pay_name;
	}

	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}
}
