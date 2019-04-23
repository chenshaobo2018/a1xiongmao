package com.zjc.users.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_account_log[zjc_account_log]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-18 11:46:15
 */
public class ZjcAccountLogPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 日志id
	 */
	private Integer log_id;
	
	/**
	 * 用户id
	 */
	private BigInteger user_id;
	
	/**
	 * 用户金额
	 */
	private BigDecimal user_money;
	
	/**
	 * 冻结金额
	 */
	private BigDecimal frozen_money;
	
	/**
	 * 支付积分
	 */
	private Integer pay_points;
	
	/**
	 * 变动时间
	 */
	private Date change_time;
	
	/**
	 * 描述
	 */
	private String descd;
	
	/**
	 * 订单编号
	 */
	private String order_sn;
	
	/**
	 * 订单id
	 */
	private Integer order_id;
	

	/**
	 * 日志id
	 * 
	 * @return log_id
	 */
	public Integer getLog_id() {
		return log_id;
	}
	
	/**
	 * 用户id
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
	}
	
	/**
	 * 用户金额
	 * 
	 * @return user_money
	 */
	public BigDecimal getUser_money() {
		return user_money;
	}
	
	/**
	 * 冻结金额
	 * 
	 * @return frozen_money
	 */
	public BigDecimal getFrozen_money() {
		return frozen_money;
	}
	
	/**
	 * 支付积分
	 * 
	 * @return pay_points
	 */
	public Integer getPay_points() {
		return pay_points;
	}
	
	/**
	 * 变动时间
	 * 
	 * @return change_time
	 */
	public Date getChange_time() {
		return change_time;
	}
	
	/**
	 * 描述
	 * 
	 * @return desc
	 */
	public String getDescd() {
		return descd;
	}
	
	/**
	 * 订单编号
	 * 
	 * @return order_sn
	 */
	public String getOrder_sn() {
		return order_sn;
	}
	
	/**
	 * 订单id
	 * 
	 * @return order_id
	 */
	public Integer getOrder_id() {
		return order_id;
	}
	

	/**
	 * 日志id
	 * 
	 * @param log_id
	 */
	public void setLog_id(Integer log_id) {
		this.log_id = log_id;
	}
	
	/**
	 * 用户id
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 用户金额
	 * 
	 * @param user_money
	 */
	public void setUser_money(BigDecimal user_money) {
		this.user_money = user_money;
	}
	
	/**
	 * 冻结金额
	 * 
	 * @param frozen_money
	 */
	public void setFrozen_money(BigDecimal frozen_money) {
		this.frozen_money = frozen_money;
	}
	
	/**
	 * 支付积分
	 * 
	 * @param pay_points
	 */
	public void setPay_points(Integer pay_points) {
		this.pay_points = pay_points;
	}
	
	/**
	 * 变动时间
	 * 
	 * @param change_time
	 */
	public void setChange_time(Date change_time) {
		this.change_time = change_time;
	}
	
	/**
	 * 描述
	 * 
	 * @param desc
	 */
	public void setDescd(String descd) {
		this.descd = descd;
	}
	
	/**
	 * 订单编号
	 * 
	 * @param order_sn
	 */
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	
	/**
	 * 订单id
	 * 
	 * @param order_id
	 */
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	

}