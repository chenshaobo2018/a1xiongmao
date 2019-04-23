	/**
 * 
 */
package com.zjc.store.dao.po;

import java.math.BigDecimal;

/**
 * @author Administrator
 *
 */
public class ZjcBillVO {
	
	/**
	 * 店铺名称
	 */
	private String store_name;
	
	/**
	 * 店铺ID
	 */
	private Integer store_id;
	
	/**
	 * 冻结资金
	 */
	private BigDecimal frozen_money;
	
	/**
	 * 待转账金额
	 */
	private BigDecimal pending_transfer;
	
	/**
	 * 已转账金额
	 */
	private BigDecimal transferred;
	
	/*
	 * 合计金额
	 */
	private BigDecimal sum_money;
	
	/**
	 * 转账金额
	 */
	private BigDecimal cost;

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public Integer getStore_id() {
		return store_id;
	}

	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}

	public BigDecimal getFrozen_money() {
		return frozen_money;
	}

	public void setFrozen_money(BigDecimal frozen_money) {
		this.frozen_money = frozen_money;
	}

	public BigDecimal getPending_transfer() {
		return pending_transfer;
	}

	public void setPending_transfer(BigDecimal pending_transfer) {
		this.pending_transfer = pending_transfer;
	}

	public BigDecimal getTransferred() {
		return transferred;
	}

	public void setTransferred(BigDecimal transferred) {
		this.transferred = transferred;
	}

	public BigDecimal getSum_money() {
		return sum_money;
	}

	public void setSum_money(BigDecimal sum_money) {
		this.sum_money = sum_money;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
}
