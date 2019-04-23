package com.zjc.users.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_provincial_generation[zjc_provincial_generation]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-01-30 09:56:27
 */
public class ZjcProvincialGenerationPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * order_id
	 */
	private Integer order_id;
	
	/**
	 * 订单总额
	 */
	private BigDecimal total_amount;
	
	/**
	 * 提成
	 */
	private BigDecimal commission;
	
	/**
	 * user_id
	 */
	private BigInteger user_id;
	
	/**
	 * 是否提成0未1提
	 */
	private Integer is_commission;
	

	/**
	 * order_id
	 * 
	 * @return order_id
	 */
	public Integer getOrder_id() {
		return order_id;
	}
	
	/**
	 * 订单总额
	 * 
	 * @return total_amount
	 */
	public BigDecimal getTotal_amount() {
		return total_amount;
	}
	
	/**
	 * 提成
	 * 
	 * @return commission
	 */
	public BigDecimal getCommission() {
		return commission;
	}
	
	/**
	 * user_id
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
	}
	
	/**
	 * 是否提成0未1提
	 * 
	 * @return is_commission
	 */
	public Integer getIs_commission() {
		return is_commission;
	}
	

	/**
	 * order_id
	 * 
	 * @param order_id
	 */
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
	/**
	 * 订单总额
	 * 
	 * @param total_amount
	 */
	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}
	
	/**
	 * 提成
	 * 
	 * @param commission
	 */
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	
	/**
	 * user_id
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 是否提成0未1提
	 * 
	 * @param is_commission
	 */
	public void setIs_commission(Integer is_commission) {
		this.is_commission = is_commission;
	}
	

}