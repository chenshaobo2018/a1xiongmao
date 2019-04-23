/**
 * 
 */
package com.zjc.store.dao.po;

import java.math.BigDecimal;

/**
 * @author Administrator
 *
 */
public class ZjcBillRecord {
	
	/**
	 * 店铺名称
	 */
	private String store_name;
	
	/**
	 * 订单下单时间
	 */
	private String add_time;
	
	/*
	 * 订单完成时间
	 */
	private String confirm_time;
	
	/**
	 * 转账完成时间
	 */
	private String transit_time;
	
	/**
	 * 转账金额
	 */
	private BigDecimal cash;
	
	/**
	 * 操作员
	 */
	private Integer operator_id;
	
	/**
	 * 审批员
	 */
	private Integer approval_id;

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public String getConfirm_time() {
		return confirm_time;
	}

	public void setConfirm_time(String confirm_time) {
		this.confirm_time = confirm_time;
	}

	public String getTransit_time() {
		return transit_time;
	}

	public void setTransit_time(String transit_time) {
		this.transit_time = transit_time;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public Integer getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(Integer operator_id) {
		this.operator_id = operator_id;
	}

	public Integer getApproval_id() {
		return approval_id;
	}

	public void setApproval_id(Integer approval_id) {
		this.approval_id = approval_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ZjcBillRecord [store_name=" + store_name + ", add_time=" + add_time + ", confirm_time=" + confirm_time
				+ ", transit_time=" + transit_time + ", cash=" + cash + ", operator_id=" + operator_id
				+ ", approval_id=" + approval_id + "]";
	}
}
