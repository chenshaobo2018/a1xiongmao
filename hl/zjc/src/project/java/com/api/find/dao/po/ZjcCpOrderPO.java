package com.api.find.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_cp_order[zjc_cp_order]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-16 11:07:35
 */
public class ZjcCpOrderPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 企业宣传订单ID
	 */
	private Integer order_id;
	
	/**
	 * 企业宣传ID
	 */
	private Integer cp_id;
	
	/**
	 * 用户ID
	 */
	private BigInteger user_id;
	
	/**
	 * 消费积分
	 */
	private BigDecimal cost_price;
	
	/**
	 * 购买时间
	 */
	private Date add_time;
	

	/**
	 * 企业宣传订单ID
	 * 
	 * @return order_id
	 */
	public Integer getOrder_id() {
		return order_id;
	}
	
	/**
	 * 企业宣传ID
	 * 
	 * @return cp_id
	 */
	public Integer getCp_id() {
		return cp_id;
	}
	
	/**
	 * 用户ID
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
	}
	
	/**
	 * 消费积分
	 * 
	 * @return cost_price
	 */
	public BigDecimal getCost_price() {
		return cost_price;
	}
	
	/**
	 * 购买时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	

	/**
	 * 企业宣传订单ID
	 * 
	 * @param order_id
	 */
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
	/**
	 * 企业宣传ID
	 * 
	 * @param cp_id
	 */
	public void setCp_id(Integer cp_id) {
		this.cp_id = cp_id;
	}
	
	/**
	 * 用户ID
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 消费积分
	 * 
	 * @param cost_price
	 */
	public void setCost_price(BigDecimal cost_price) {
		this.cost_price = cost_price;
	}
	
	/**
	 * 购买时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	

}