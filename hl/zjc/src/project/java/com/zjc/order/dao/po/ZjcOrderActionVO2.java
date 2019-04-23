/**
 * 
 */
package com.zjc.order.dao.po;

import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * @author Administrator
 *
 */
public class ZjcOrderActionVO2 extends PO{
	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer action_id;
	
	/**
	 * 订单ID
	 */
	private Integer order_id;
	
	/**
	 * 操作人 0 为管理员操作
	 */
	private BigInteger action_user;
	
	/**
	 *  1用户 ，2 管理员  3，商家用户ID
	 */
	private Integer action_user_type;
	
	/**
	 * 订单状态
	 */
	private String order_status;
	
	/**
	 * 配送状态
	 */
	private String shipping_status;
	
	/**
	 * 支付状态
	 */
	private String pay_status;
	
	/**
	 * 操作备注
	 */
	private String action_note;
	
	/**
	 * 操作时间
	 */
	private Date log_time;
	
	public Integer getAction_id() {
		return action_id;
	}

	public void setAction_id(Integer action_id) {
		this.action_id = action_id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public BigInteger getAction_user() {
		return action_user;
	}

	public void setAction_user(BigInteger action_user) {
		this.action_user = action_user;
	}

	public Integer getAction_user_type() {
		return action_user_type;
	}

	public void setAction_user_type(Integer action_user_type) {
		this.action_user_type = action_user_type;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getShipping_status() {
		return shipping_status;
	}

	public void setShipping_status(String shipping_status) {
		this.shipping_status = shipping_status;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

	public String getAction_note() {
		return action_note;
	}

	public void setAction_note(String action_note) {
		this.action_note = action_note;
	}

	public Date getLog_time() {
		return log_time;
	}

	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}

	public String getStatus_desc() {
		return status_desc;
	}

	public void setStatus_desc(String status_desc) {
		this.status_desc = status_desc;
	}

	/**
	 * 状态描述
	 */
	private String status_desc;
}
