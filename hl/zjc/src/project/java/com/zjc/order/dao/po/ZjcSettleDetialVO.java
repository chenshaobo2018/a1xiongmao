/**
 * 
 */
package com.zjc.order.dao.po;

/**
 * @author Administrator
 *
 */
public class ZjcSettleDetialVO {
	/**
	 * 易物劵结算输入
	 */
	private Integer setIn;
	/**
	 * 客户支付
	 */
	private Integer customer_pay;
	/**
	 * 客户钱包增长值
	 */
	private Integer customer_wallet;
	/**
	 * 实体店收入
	 */
	private Integer store_income;
	/**
	 * 结算补贴
	 */
	private Integer subsidy;
	public Integer getSetIn() {
		return setIn;
	}
	public void setSetIn(Integer setIn) {
		this.setIn = setIn;
	}
	public Integer getCustomer_pay() {
		return customer_pay;
	}
	public void setCustomer_pay(Integer customer_pay) {
		this.customer_pay = customer_pay;
	}
	public Integer getCustomer_wallet() {
		return customer_wallet;
	}
	public void setCustomer_wallet(Integer customer_wallet) {
		this.customer_wallet = customer_wallet;
	}
	public Integer getStore_income() {
		return store_income;
	}
	public void setStore_income(Integer store_income) {
		this.store_income = store_income;
	}
	public Integer getSubsidy() {
		return subsidy;
	}
	public void setSubsidy(Integer subsidy) {
		this.subsidy = subsidy;
	}
	public ZjcSettleDetialVO(Integer setIn, Integer customer_pay, Integer customer_wallet, Integer store_income,
			Integer subsidy) {
		super();
		this.setIn = setIn;
		this.customer_pay = customer_pay;
		this.customer_wallet = customer_wallet;
		this.store_income = store_income;
		this.subsidy = subsidy;
	}
}
