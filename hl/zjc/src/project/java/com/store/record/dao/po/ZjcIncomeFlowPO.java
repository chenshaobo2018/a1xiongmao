package com.store.record.dao.po;

import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_income_flow[zjc_income_flow]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-09-11 18:04:39
 */
public class ZjcIncomeFlowPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表ID
	 */
	private BigInteger id;
	
	/**
	 * 交易时间
	 */
	private Date exchange_time;
	
	/**
	 * 订单ID
	 */
	private String order_sn;
	
	/**
	 * 收入卷
	 */
	private Integer income;
	
	/**
	 * 支出卷
	 */
	private Integer expend;
	
	/**
	 * 当前账户可转易物劵
	 */
	private Integer balance;
	
	/**
	 * 收支类型：0会员商城购物获得券；2会员线下购物获得券；1商家从商家后台转账到自己账号
	 */
	private String type;
	
	/**
	 * 收入方ID
	 */
	private Integer in_user_id;
	
	/**
	 * 支出方
	 */
	private Integer out_user_id;
	

	/**
	 * 表ID
	 * 
	 * @return id
	 */
	public BigInteger getId() {
		return id;
	}
	
	/**
	 * 交易时间
	 * 
	 * @return exchange_time
	 */
	public Date getExchange_time() {
		return exchange_time;
	}
	
	/**
	 * 订单ID
	 * 
	 * @return order_id
	 */
	public String getOrder_sn() {
		return order_sn;
	}
	
	/**
	 * 收入卷
	 * 
	 * @return income
	 */
	public Integer getIncome() {
		return income;
	}
	
	/**
	 * 支出卷
	 * 
	 * @return expend
	 */
	public Integer getExpend() {
		return expend;
	}
	
	/**
	 * 当前账户可转易物劵
	 * 
	 * @return balance
	 */
	public Integer getBalance() {
		return balance;
	}
	
	/**
	 * 收支类型：0会员商城购物获得券；2会员线下购物获得券；1商家从商家后台转账到自己账号
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 收入方ID
	 * 
	 * @return in_user_id
	 */
	public Integer getIn_user_id() {
		return in_user_id;
	}
	
	/**
	 * 支出方
	 * 
	 * @return out_user_id
	 */
	public Integer getOut_user_id() {
		return out_user_id;
	}
	

	/**
	 * 表ID
	 * 
	 * @param id
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	/**
	 * 交易时间
	 * 
	 * @param exchange_time
	 */
	public void setExchange_time(Date exchange_time) {
		this.exchange_time = exchange_time;
	}
	
	/**
	 * 订单ID
	 * 
	 * @param order_id
	 */
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	
	/**
	 * 收入卷
	 * 
	 * @param income
	 */
	public void setIncome(Integer income) {
		this.income = income;
	}
	
	/**
	 * 支出卷
	 * 
	 * @param expend
	 */
	public void setExpend(Integer expend) {
		this.expend = expend;
	}
	
	/**
	 * 当前账户可转易物劵
	 * 
	 * @param balance
	 */
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
	/**
	 * 收支类型：0会员商城购物获得券；2会员线下购物获得券；1商家从商家后台转账到自己账号
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 收入方ID
	 * 
	 * @param in_user_id
	 */
	public void setIn_user_id(Integer in_user_id) {
		this.in_user_id = in_user_id;
	}
	
	/**
	 * 支出方
	 * 
	 * @param out_user_id
	 */
	public void setOut_user_id(Integer out_user_id) {
		this.out_user_id = out_user_id;
	}
	

}